package hr.rma.db.assecoforecast.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import hr.rma.db.assecoforecast.data.models.Current
import hr.rma.db.assecoforecast.data.models.Daily
import hr.rma.db.assecoforecast.data.models.Hourly
import hr.rma.db.assecoforecast.data.databases.ForecastDatabase
import hr.rma.db.assecoforecast.data.network.ForecastNetworkDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ForecastRepository(
    application: Application,
    private val database: ForecastDatabase = ForecastDatabase.getInstance(application),
    private val TAG : String  = "ForecastRepository"
) {
    private val network = ForecastNetworkDataSourceImpl(application.applicationContext)

    suspend fun insertWeather() {
        withContext(Dispatchers.IO) {
            val weather = network.getForecast()
            val current = weather?.current
            val hourly = weather?.hourly
            val daily = weather?.daily
            if (current != null) {
                database.forecastDao().insertCurrentWeather(current)
            }
            if (hourly != null) {
                database.forecastDao().insertHourlyWeather(*hourly.toTypedArray())
            }
            if (daily != null) {
                database.forecastDao().insertDailyWeather(*daily.toTypedArray())
            }
        }
    }

    suspend fun deleteCurrent(){
        withContext(Dispatchers.IO) {
            database.forecastDao().deleteCurrent()
        }
    }

    suspend fun deleteHourly(){
        withContext(Dispatchers.IO) {
            database.forecastDao().deleteHourly()
        }
    }

    suspend fun deleteDaily(){
        withContext(Dispatchers.IO) {
            database.forecastDao().deleteDaily()
        }
    }

    fun getCurrentWeatherCount(): LiveData<Int>? = database.forecastDao().getCurrentWeatherCount()

    fun getCurrent(): LiveData<Current?>? = database.forecastDao().getCurrentWeather()

    fun getHourly(): LiveData<List<Hourly?>?>? = database.forecastDao().getHourlyWeather()

    fun getDaily(): LiveData<List<Daily?>?>? = database.forecastDao().getDailyWeather()
}