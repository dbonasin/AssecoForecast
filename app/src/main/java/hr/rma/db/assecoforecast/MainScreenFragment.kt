package hr.rma.db.assecoforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import hr.riteh.db.assecotest.MainScreenViewModel
import kotlinx.coroutines.runBlocking

class MainScreenFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private lateinit var viewModel: MainScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)

        viewModel.getCurrentWeatherCount()?.observe(viewLifecycleOwner, Observer {
                t->
                runBlocking{ viewModel.getData() }
        })

        val changeCityBtn = view?.findViewById<ImageButton>(R.id.bt_change_city)
        changeCityBtn?.setOnClickListener(this)

        val tvCurrTemp: TextView = requireView().findViewById(R.id.tv_current_temperature)
        val tvHumidity: TextView = requireView().findViewById(R.id.tv_current_humidity)
        val tvCityName: TextView = requireView().findViewById(R.id.tv_city_name)
        val tvDescription: TextView = requireView().findViewById(R.id.tv_current_weather)
        val ivWeather: ImageView = requireView().findViewById(R.id.iv_weather_icon)

        val circularProgressDrawable = CircularProgressDrawable(requireView().context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(circularProgressDrawable)
        viewModel.getCurrent()?.observe(viewLifecycleOwner,
            Observer{ t ->
                var strTmp = t?.currentTemp.toString() + "°C"
                var strHum = t?.currentHumidity.toString() + "%"
                var description = t?.description

                if (strTmp.compareTo("null°C") == 0){
                    strTmp = ""
                    strHum = ""
                    description = getString(R.string.loading)
                }
                tvCurrTemp.text = strTmp
                tvHumidity.text = strHum
                tvDescription.text = description
                val imageURL = "http://openweathermap.org/img/wn/" + t?.icon + "@2x.png"

                Glide.with(this).load(imageURL).apply(options)
                    .into(ivWeather)
            })

        tvCityName.text = viewModel.getCityName()

    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.bt_change_city){

            val fragment = CitySearchFragment()

            parentFragmentManager
                .beginTransaction()
                .hide(parentFragmentManager.findFragmentByTag("forecastFragment")!!)
                .replace(R.id.fragment_curr_weather, fragment, "citySearch")
                .commit()

        }

    }

}
