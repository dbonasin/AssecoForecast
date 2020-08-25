package hr.rma.db.assecoforecast

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import hr.rma.db.assecoforecast.adapters.CityListAdapter
import hr.rma.db.assecoforecast.data.models.City
import kotlinx.coroutines.runBlocking


class CitySearchFragment : Fragment(), CityListAdapter.ListItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private val adapter: CityListAdapter = CityListAdapter(this)
    private lateinit var bckButton: ImageButton
    private lateinit var searchBox: TextInputEditText

    companion object {
        fun newInstance() = CitySearchFragment()
    }

    private lateinit var viewModel: CitySearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.city_search_fragment, container, false)
        recyclerView = view.findViewById(R.id.rv_city_list)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CitySearchFragmentViewModel::class.java)

        bckButton = requireView().findViewById(R.id.ib_back)
        bckButton.setOnClickListener { v ->
            if (v.id == R.id.ib_back){
                val fragmentCurr = MainScreenFragment()
                val fragmentForecast = ForecastScreenFragment()

                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_curr_weather, fragmentCurr, "mainFragment")
                    .replace(R.id.fragment_forecast, fragmentForecast, "forecastFragment")
                    .commit()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel.getAllCities()?.observe(viewLifecycleOwner,
            Observer<List<City?>?> { t ->
                adapter.setCities(t)
            })

        searchBox = requireView().findViewById(R.id.tiet_city_search)
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
//                ("Not yet implemented")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                ("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                viewModel.searchCities(s.toString() + "%")?.observe(viewLifecycleOwner,
                    Observer { t->
                        adapter.setCities(t)
                    })

            }

        }
        searchBox.addTextChangedListener(textWatcher)
    }

    override fun onListItemClick(clickedCityName: String) {

        Log.d("SearchFragment", "Kliknuo si")
        Log.d("SearchFragment", clickedCityName)
        runBlocking{ viewModel.makeForecastBlank() }

        viewModel.getLatAndLon(clickedCityName)?.observe(viewLifecycleOwner, Observer {
                t->
//            val sharedPreferences = activity?.getSharedPreferences(
//                "MY_PREF",
//                Context.MODE_PRIVATE)
//            val editor = sharedPreferences?.edit()
//            editor?.putString("CITY_LON", t.capitalLongitude)
//            editor?.putString("CITY_LAT", t.capitalLatitude)
//            editor?.apply()
            viewModel.setLat(t.capitalLatitude!!)
            viewModel.setLon(t.capitalLongitude!!)
        })

//        val sharedPreferences = activity?.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
//        val editor = sharedPreferences?.edit()
//        editor?.putString("CITY_NAME", clickedCityName)
//        editor?.apply()
        viewModel.setCityName(clickedCityName)

        val fragmentCurr = MainScreenFragment()
        val fragmentForecast = ForecastScreenFragment()

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_curr_weather, fragmentCurr, "mainFragment")
            .replace(R.id.fragment_forecast, fragmentForecast, "forecastFragment")
            .commit()
    }

}
