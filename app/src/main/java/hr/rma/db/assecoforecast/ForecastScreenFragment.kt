package hr.rma.db.assecoforecast

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.rma.db.assecoforecast.adapters.DailyAdapter
import hr.rma.db.assecoforecast.adapters.HourlyAdapter
import hr.rma.db.assecoforecast.data.models.Daily
import hr.rma.db.assecoforecast.data.models.Hourly


class ForecastScreenFragment : Fragment() {

    private val hourlyAdapter: HourlyAdapter = HourlyAdapter()
    private val dailyAdapter: DailyAdapter = DailyAdapter()

    companion object {
        fun newInstance() = ForecastScreenFragment()
    }

    private lateinit var viewModel: ForecastScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.forecast_screen_fragment, container, false)
        val activity = activity as Context

        val dailyRecyclerView = view.findViewById<RecyclerView>(R.id.daily_forecast)
        val hourlyRecyclerView = view.findViewById<RecyclerView>(R.id.hourly_forecast)

        dailyRecyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false)
        hourlyRecyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false)

        dailyRecyclerView.setHasFixedSize(true)
        hourlyRecyclerView.setHasFixedSize(true)

        dailyRecyclerView.adapter = dailyAdapter
        hourlyRecyclerView.adapter = hourlyAdapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForecastScreenViewModel::class.java)

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
