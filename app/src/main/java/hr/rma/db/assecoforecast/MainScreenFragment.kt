package hr.rma.db.assecoforecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


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

        val changeCityBtn = view.findViewById<ImageButton>(R.id.bt_change_city)
        changeCityBtn.setOnClickListener(this)

        val tvCurrTemp: TextView = view.findViewById(R.id.tv_current_temperature)
        val tvHumidity: TextView = view.findViewById(R.id.tv_current_humidity)
        val tvCityName: TextView = view.findViewById(R.id.tv_city_name)
        val tvDescription: TextView = view.findViewById(R.id.tv_current_weather)
        val ivWeather: ImageView = view.findViewById(R.id.iv_weather_icon)

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)


        viewModel.getCurrent()?.observe(viewLifecycleOwner,
            Observer{ t ->
                val strTmp = t?.currentTemp.toString() + "°C"
                val strHum = t?.currentHumidity.toString() + "%"
                tvCurrTemp.text = strTmp
                tvHumidity.text = strHum
                tvDescription.text = t?.description
                val imageURL = "http://openweathermap.org/img/wn/" + t?.icon + "@2x.png"

                Glide.with(this).load(imageURL).apply(options)
                    .into(ivWeather)
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