package hr.rma.db.assecoforecast.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import hr.rma.db.assecoforecast.data.dao.CityDao
import hr.rma.db.assecoforecast.data.models.City
import hr.rma.db.assecoforecast.data.databases.CityDatabase
import hr.rma.db.assecoforecast.data.provider.SharedPrefsProviderImpl

class CityRepository(application: Application) {
    private val TAG: String = "CityRepository"

    private var cityDao: CityDao? = null
    private val sharedPreferencesProvider = SharedPrefsProviderImpl(application)

    fun getAllCities() = cityDao?.getAllCities()

    fun searchCities(cityName: String) = cityDao?.searchCities(cityName)

    fun getCityName() = sharedPreferencesProvider.getCityName()

    fun setCityName(cityName: String) = sharedPreferencesProvider.setCityName(cityName)

    fun setLat(lat: String) = sharedPreferencesProvider.setLat(lat)

    fun setLon(lon: String) = sharedPreferencesProvider.setLon(lon)

    fun getLatAndLon(cityName: String): LiveData<City>?{
        val city = cityDao?.getLatAndLon(cityName)
        Log.d(TAG,  "ušao sam u getLatAndLon u CityRepository, ime grada: " + city?.value?.capitalName )
        return city
    }

    //
    init {
        val database: CityDatabase? = CityDatabase.getInstance(application)
        cityDao = database?.cityDao()
    }
}