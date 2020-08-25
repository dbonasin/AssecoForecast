package hr.rma.db.assecoforecast

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import hr.rma.db.assecoforecast.data.repository.CityRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val cityRepository = CityRepository(application)
    private val cityName = cityRepository.getCityName()

    fun cityNameCheck(){

        if (cityName == null){
            Log.d("M.A.V.M.", "provjeramvam da li je upisan grad u shared pref")
            cityRepository.setCityName("Zagreb")
            cityRepository.setLon(16.0000.toString())
            cityRepository.setLat(45.8000.toString())
        }
    }
}