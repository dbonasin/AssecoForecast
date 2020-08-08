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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import hr.rma.db.assecoforecast.adapters.CityListAdapter
import hr.rma.db.assecoforecast.adapters.DailyAdapter
import hr.rma.db.assecoforecast.adapters.HourlyAdapter
import hr.rma.db.assecoforecast.database.Daily
import hr.rma.db.assecoforecast.database.Hourly

class ForecastScreenFragment : Fragment(){

    private lateinit var viewModel: ForecastViewModel
    private var hourlyAdapter: HourlyAdapter = HourlyAdapter()
    private var dailyAdapter: DailyAdapter = DailyAdapter()

    companion object{
        fun newInstance(): ForecastScreenFragment{
            return ForecastScreenFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)
        val activity = activity as Context
        val dailyRecyclerView = view.findViewById<RecyclerView>(R.id.daily_forecast)
        val hourlyRecyclerView = view.findViewById<RecyclerView>(R.id.hourly_forecast)
        dailyRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        hourlyRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        dailyRecyclerView.setHasFixedSize(true)
        hourlyRecyclerView.setHasFixedSize(true)
        dailyRecyclerView.adapter = dailyAdapter
        hourlyRecyclerView.adapter = hourlyAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get<ForecastViewModel>(ForecastViewModel::class.java)

        viewModel.getHourly()?.observe(viewLifecycleOwner,
            Observer<List<Hourly?>?> { t ->
             hourlyAdapter.setWeather(t)
            })
        viewModel.getDaily()?.observe(viewLifecycleOwner,
            Observer<List<Daily?>?> { t ->
                dailyAdapter.setWeather(t)
            })

    }
}