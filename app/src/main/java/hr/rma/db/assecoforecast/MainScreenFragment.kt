package hr.rma.db.assecoforecast

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.replace

class MainScreenFragment : Fragment(), View.OnClickListener{


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
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: ForecastViewModel = ViewModelProvider(this).get<ForecastViewModel>(ForecastViewModel::class.java)

        val changeCityBtn = view.findViewById<MaterialButton>(R.id.bt_change_city)
        changeCityBtn.setOnClickListener(this)

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
        val sharedPreferences = activity?.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
        tvCityName.text = sharedPreferences?.getString("CITY_NAME", null)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.bt_change_city){

            val fragment = SearchScreenFragment()

                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_curr_weather, fragment, "citySearch")
                    .commit()

        }

    }

}