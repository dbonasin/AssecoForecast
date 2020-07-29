package hr.rma.db.assecoforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainScreenFragment : Fragment(){


    companion object{
        fun newInstance(): MainScreenFragment{
            return MainScreenFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: ForecastViewModel = ViewModelProvider(this).get<ForecastViewModel>(ForecastViewModel::class.java)

        val tvCurrTemp: TextView = view.findViewById(R.id.tv_current_temperature)
        val tvHumidity: TextView = view.findViewById(R.id.tv_current_humidity)
        val tvCityName: TextView = view.findViewById(R.id.tv_city_name)

        viewModel.getCurrent()?.observe(viewLifecycleOwner,
            Observer{ t ->
                val strTmp = t?.currentTemp.toString() + "°C"
                val strHum = t?.currentHumidity.toString() + "%"
                tvCurrTemp.text = strTmp
                tvHumidity.text = strHum
            })
    }
}