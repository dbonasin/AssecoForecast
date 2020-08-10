package hr.rma.db.assecoforecast

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


class MainActivity : AppCompatActivity() {
    val TAG = "Main activity"

//    TODO kada nema mreže što da se događa

    lateinit var forecastViewModel : ForecastViewModel
    lateinit var pullToRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("MY_PREF", MODE_PRIVATE)
        val cityName = sharedPreferences.getString("CITY_NAME", null)
        val editor = sharedPreferences.edit()
        val noNetworkLayout = findViewById<LinearLayout>(R.id.empty_LinearLayout)

        forecastViewModel = ViewModelProvider(this).get<ForecastViewModel>(ForecastViewModel::class.java)

        if (cityName == null){

            editor.putString("CITY_NAME", "Zagreb")
            editor.putString("CITY_LON", 16.0000.toString())
            editor.putString("CITY_LAT", 45.8000.toString())
            editor.commit()
        }

        forecastViewModel.hasNetwork.observe(this, Observer{
            hasConnection->
            if (hasConnection){
                Log.d(TAG, "ima mreže")
                noNetworkLayout.visibility = View.INVISIBLE

                if (savedInstanceState == null) {
                    supportFragmentManager.beginTransaction()
//                        .add(R.id.fragment_curr_weather, SearchScreenFragment(), "mainFragment")
                        .add(R.id.fragment_curr_weather, MainScreenFragment(), "mainFragment")
                        .add(R.id.fragment_forecast, ForecastScreenFragment(), "forecastFragment")
                        .commit()
                }
            } else {
                Log.d(TAG, "nema mreže")
                noNetworkLayout.visibility = View.VISIBLE
                if (savedInstanceState != null) {
                    supportFragmentManager.beginTransaction()
                        .hide(supportFragmentManager.findFragmentByTag("mainFragment")!!)
                        .hide(supportFragmentManager.findFragmentByTag("forecastFragment")!!)
                        .commit()
                }
//                forecastViewModel.getCurrentWeatherCount()?.observe(this, Observer {
//                        count->
//                    if (count!=0 && !hasConnection) forecastViewModel.getData()
//                })

            }

        })



//        pullToRefresh = findViewById(R.id.pull_to_refresh);
////        val forecastViewModel = ViewModelProvider(this).get<ForecastViewModel>(ForecastViewModel::class.java)
//
//        pullToRefresh.setOnRefreshListener {
//            forecastViewModel.getData()
//            pullToRefresh.isRefreshing = false
//        }

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
                return true;
            }
        }
        return super.onKeyDown(keyCode, event)
    }
//
//    fun disableSwipe() {
//        pullToRefresh.isEnabled = false
//    }
//

}