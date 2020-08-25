package hr.riteh.db.assecotest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import hr.rma.db.assecoforecast.data.models.Current
import hr.rma.db.assecoforecast.data.repository.CityRepository
import hr.rma.db.assecoforecast.data.repository.ForecastRepository
import hr.rma.db.assecoforecast.data.response.WeatherResponse

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {
    val TAG = "ViewModel"

    private var repository: ForecastRepository = ForecastRepository(application)
    private var cityRepository: CityRepository = CityRepository(application)

    fun getCurrentWeatherCount() = repository.getCurrentWeatherCount()

    fun getCurrent(): LiveData<Current?>? {
        return repository.getCurrent()
    }

    fun getCityName() = cityRepository.getCityName()

    suspend fun getData(){
            repository.insertWeather()
    }
}
