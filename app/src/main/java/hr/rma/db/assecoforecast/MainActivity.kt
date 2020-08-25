package hr.rma.db.assecoforecast

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    val TAG = "Main activity"

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.cityNameCheck()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_curr_weather, CitySearchFragment(), "mainFragment")
                .add(R.id.fragment_curr_weather, MainScreenFragment(), "mainFragment")
                .add(R.id.fragment_forecast, ForecastScreenFragment(), "forecastFragment")
                .commit()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val searchFragment = supportFragmentManager.findFragmentByTag("citySearch")
        if (searchFragment != null && searchFragment.isVisible){
            if (keyCode == KeyEvent.KEYCODE_BACK ) {
                val fragmentCurr = MainScreenFragment()
                val fragmentForecast = ForecastScreenFragment()

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_curr_weather, fragmentCurr, "mainFragment")
                    .replace(R.id.fragment_forecast, fragmentForecast, "forecastFragment")
                    .commit()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

}
