package hr.rma.db.assecoforecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.rma.db.assecoforecast.adapters.CityListAdapter
import hr.rma.db.assecoforecast.adapters.CityListAdapter.ListItemClickListener
import hr.rma.db.assecoforecast.database.City

class SearchScreenFragment :Fragment(), ListItemClickListener {

    private lateinit var viewModel: ForecastViewModel
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
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_city_list)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get<ForecastViewModel>(ForecastViewModel::class.java)


        viewModel.getAllCities()?.observe(viewLifecycleOwner,
            Observer<List<City?>?> { t ->
                adapter.setCities(t)
            })
    }

    override fun onListItemClick(clickedItemIndex: Int) {
        TODO("Not yet implemented")
    }
}