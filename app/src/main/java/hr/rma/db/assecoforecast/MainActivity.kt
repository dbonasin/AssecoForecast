package hr.rma.db.assecoforecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    val TAG = "Main activity"

//    todo napraviti loading animaciju,
//    todo napraviti da čovjek dam može napraviti refresh,
//     todo napraviti da se refresha svaki sat

    lateinit var forecastViewModel : ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("MY_PREF", MODE_PRIVATE)
        val cityName = sharedPreferences.getString("CITY_NAME", null)

        if (cityName == null){
            val editor = sharedPreferences.edit()
            editor.putString("CITY_NAME", "Zagreb")
            editor.apply()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
//            .add(R.id.fragment_curr_weather, SearchScreenFragment(), "mainFragment")
                .add(R.id.fragment_curr_weather, MainScreenFragment(), "mainFragment")
                .add(R.id.fragment_forecast, ForecastScreenFragment(), "forecastFragment")
                .commit()
        }

        forecastViewModel = ViewModelProvider(this).get<ForecastViewModel>(ForecastViewModel::class.java)
        forecastViewModel.getCurrentWeatherCount()?.observe(this, Observer {
            t->
            forecastViewModel.isEmpty = false
        })
        forecastViewModel.getData()
    }
}
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//import androidx.viewpager2.adapter.FragmentStateAdapter
//import androidx.viewpager2.widget.ViewPager2
//
///**
// * The number of pages (wizard steps) to show in this demo.
// */
//private const val NUM_PAGES = 2
//
//class MainActivity : FragmentActivity() {
//
//    /**
//     * The pager widget, which handles animation and allows swiping horizontally to access previous
//     * and next wizard steps.
//     */
//    private lateinit var viewPager: ViewPager2
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Instantiate a ViewPager2 and a PagerAdapter.
//        viewPager = findViewById(R.id.pager)
//
//        // The pager adapter, which provides the pages to the view pager widget.
//        val pagerAdapter = ScreenSlidePagerAdapter(this)
//        viewPager.adapter = pagerAdapter
//    }
//
//    override fun onBackPressed() {
//        if (viewPager.currentItem == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed()
//        } else {
//            // Otherwise, select the previous step.
//            viewPager.currentItem = viewPager.currentItem - 1
//        }
//    }
//
//    /**
//     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
//     * sequence.
//     */
//    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
//        override fun getItemCount(): Int = NUM_PAGES
//
//        override fun createFragment(position: Int): Fragment = ForecastScreenFragment()
//    }
//}



