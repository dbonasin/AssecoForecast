package hr.rma.db.assecoforecast

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.rma.db.assecoforecast.database.Hourly

class MainActivity : AppCompatActivity() {
    val TAG = "Main activity"

    lateinit var tvCurrTemperature : TextView
    lateinit var tvCurrWeather : TextView
    lateinit var tvMaxTemp : TextView
    lateinit var tvMinTemp : TextView
    lateinit var weatherResponse: WeatherResponse
    lateinit var forecastViewModel : ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

//        if (ContextCompat.checkSelfPermission(
//                this@MainActivity,
//                Manifest.permission.WRITE_CALENDAR
//            )
//            !== PackageManager.PERMISSION_GRANTED
//        ) {
//            requestPermissions(
//                this@MainActivity,
//                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
//                1
//            )
//        } else {
//            //sve se nastavlja
//        }



        tvCurrTemperature = findViewById(R.id.current_temperature)
        tvCurrWeather = findViewById(R.id.current_weather)
        tvMaxTemp = findViewById(R.id.max_temp)
        tvMinTemp = findViewById(R.id.min_temp)
        forecastViewModel = ViewModelProvider(this).get<ForecastViewModel>(ForecastViewModel::class.java)
        forecastViewModel.getData()
//        forecastViewModel.getCurrent()?.observe(this,
//            Observer<Current?> { t ->
//                tvCurrTemperature.text = t?.currentTemp.toString()
//                tvCurrWeather.text = t?.clouds.toString()
//            })
        forecastViewModel.getHourly()?.observe(this,
            Observer<List<Hourly?>?> {
                t ->
                tvCurrTemperature.text = t?.get(0)?.hourTemp.toString()
            })
    }
}


