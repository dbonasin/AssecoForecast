package hr.rma.db.assecoforecast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.rma.db.assecoforecast.ForecastViewModel
import hr.rma.db.assecoforecast.WeatherResponse

class ForecastScreenFragment : Fragment() {
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        forecastViewModel = ViewModelProvider(this).get<ForecastViewModel>(ForecastViewModel::class.java)
//
//        forecastViewModel.getData()?.observe(viewLifecycleOwner, object :
//            Observer<WeatherResponse?> {
//            override fun onChanged(t: WeatherResponse?) {
//                tvCurrTemperature.setText(t?.current?.temp.toString())
//                tvCurrWeather.setText(t?.current?.weather.toString())
//            }
//
//        })
//    }
}