package hr.rma.db.assecoforecast

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import hr.rma.db.assecoforecast.adapters.CityListAdapter
import hr.rma.db.assecoforecast.adapters.CityListAdapter.ListItemClickListener
import hr.rma.db.assecoforecast.database.City

class SearchScreenFragment :Fragment(), ListItemClickListener {

    private lateinit var viewModel: ForecastViewModel
    private lateinit var recyclerView: RecyclerView
    private var adapter: CityListAdapter = CityListAdapter(this)
    private lateinit var bckButton: ImageButton
    private lateinit var searchBox: TextInputEditText

    companion object{
        fun newInstance(): SearchScreenFragment{
            return SearchScreenFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //        TODO dodati da se snappa na sredinu
        val view = inflater.inflate(R.layout.fragment_city_search, container, false)
        recyclerView = view.findViewById(R.id.rv_city_list)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bckButton = view.findViewById(R.id.ib_back)
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

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get<ForecastViewModel>(ForecastViewModel::class.java)

        viewModel.getAllCities()?.observe(viewLifecycleOwner,
            Observer<List<City?>?> { t ->
                adapter.setCities(t)
        })

        searchBox = view.findViewById(R.id.tiet_city_search)
        val textWatcher = object : TextWatcher{
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

//        (activity as MainActivity?)!!.disableSwipe()
    }

    override fun onListItemClick(clickedCityName: String) {

        Log.d("SearchFragment", "Kliknuo si")
        Log.d("SearchFragment", clickedCityName)

        viewModel.getLatAndLon(clickedCityName)?.observe(viewLifecycleOwner, Observer {
                t->
            val sharedPreferences = activity?.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.putString("CITY_LON", t.capitalLongitude)
            editor?.putString("CITY_LAT", t.capitalLatitude)
            editor?.apply()
            viewModel.getData()
        })

        val sharedPreferences = activity?.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("CITY_NAME", clickedCityName)
        editor?.apply()

//            viewModel.city = viewModel.getLatAndLon(t[clickedItemIndex]?.capitalName!!)

        val fragmentCurr = MainScreenFragment()
        val fragmentForecast = ForecastScreenFragment()

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_curr_weather, fragmentCurr, "mainFragment")
            .replace(R.id.fragment_forecast, fragmentForecast, "forecastFragment")
            .commit()
    }


}