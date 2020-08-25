package hr.rma.db.assecoforecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import hr.rma.db.assecoforecast.data.models.City
import hr.rma.db.assecoforecast.data.repository.CityRepository
import hr.rma.db.assecoforecast.data.repository.ForecastRepository

class CitySearchFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val TAG = "ViewModel"

    private var repository: ForecastRepository = ForecastRepository(application)
    private var cityRepository: CityRepository = CityRepository(application)

    fun getLatAndLon(cityName: String) : LiveData<City>? {
        return cityRepository.getLatAndLon(cityName)
    }

    fun getAllCities() =  cityRepository.getAllCities()

    fun searchCities(cityName: String) = cityRepository.searchCities(cityName)

    fun setCityName(cityName: String) = cityRepository.setCityName(cityName)

    fun setLon(lon: String) = cityRepository.setLon(lon)

    fun setLat(lat: String) = cityRepository.setLat(lat)

    suspend fun makeForecastBlank(){
        repository.deleteCurrent()
        repository.deleteHourly()
        repository.deleteDaily()
    }

}
