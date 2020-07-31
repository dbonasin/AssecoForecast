package hr.rma.db.assecoforecast

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.rma.db.assecoforecast.adapters.CityListAdapter
import hr.rma.db.assecoforecast.adapters.CityListAdapter.ListItemClickListener
import hr.rma.db.assecoforecast.database.City

class SearchScreenFragment :Fragment(), ListItemClickListener {
    //TODO trebati ću probati ići po googlovom tutorialu

    private lateinit var viewModel: ForecastViewModel
    private lateinit var recyclerView: RecyclerView
    private var adapter: CityListAdapter = CityListAdapter(this)

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

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get<ForecastViewModel>(ForecastViewModel::class.java)


        viewModel.getAllCities()?.observe(viewLifecycleOwner,
            Observer<List<City?>?> { t ->
                adapter.setCities(t)
            })
    }
//
    override fun onListItemClick(clickedItemIndex: Int) {

        viewModel.getAllCities()?.observe(viewLifecycleOwner, Observer<List<City?>?> { t->
            val cityName = t?.get(clickedItemIndex)?.capitalName!!
            Log.d("SearchFragment", "Kliknuo si")
            Log.d("SearchFragment", cityName)

            viewModel.getLatAndLon(cityName)?.observe(viewLifecycleOwner, Observer {
                t->
                val sharedPreferences = activity?.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
                val editor = sharedPreferences?.edit()
                editor?.putString("CITY_LON", t.capitalLongitude)
                editor?.putString("CITY_LAT", t.capitalLatitude)
                editor?.apply()
            })

            val sharedPreferences = activity?.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.putString("CITY_NAME", cityName)
            editor?.apply()

//            viewModel.city = viewModel.getLatAndLon(t[clickedItemIndex]?.capitalName!!)
            viewModel.getData()

            val fragmentCurr = MainScreenFragment()
            val fragmentForecast = ForecastScreenFragment()

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_curr_weather, fragmentCurr, "mainFragment")
                .replace(R.id.fragment_forecast, fragmentForecast, "forecastFragment")
                .commit()
        })
    }
}