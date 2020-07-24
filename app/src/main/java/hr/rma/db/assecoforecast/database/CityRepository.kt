package hr.rma.db.assecoforecast.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CityRepository(application: Application) {
    private val TAG: String = "CityRepository"

    private var cityDao: CityDao? = null

    fun getAllCities() = cityDao?.getAllCities()

    fun getNumberOfCities() = cityDao?.getNumberOFCities()

    fun getLatAndLon(cityName: String) : LiveData<City>?{
        return cityDao?.getLatAndLon(cityName)
    }

    //
    init {
        val database: CityDatabase? = CityDatabase.getInstance(application)
        cityDao = database?.cityDao()
    }
}