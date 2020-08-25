package hr.rma.db.assecoforecast.data.network

import android.content.Context
import hr.rma.db.assecoforecast.data.createApiService
import hr.rma.db.assecoforecast.data.models.Current
import hr.rma.db.assecoforecast.data.models.Daily
import hr.rma.db.assecoforecast.data.models.Hourly
import hr.rma.db.assecoforecast.data.models.Weather
import hr.rma.db.assecoforecast.data.provider.SharedPrefsProviderImpl
import java.util.concurrent.TimeUnit

class ForecastNetworkDataSourceImpl(
    private val context: Context
) : ForecastNetworkDataSource {

    private val API_KEY = "659e20ae4addd2c6c99d190ee44beca4"
    private val  baseUrl = "https://api.openweathermap.org/"
    private val units = "metric"
    private val exclude = "minutely"
    private val TAG = "F.N.D.S.I."

    override suspend fun getForecast() : Weather? {

        val sharedPreferences = SharedPrefsProviderImpl(context)
        val lat = sharedPreferences.getLat()?.toDouble()
        val lon = sharedPreferences.getLon()?.toDouble()
        val dataTimestamp = sharedPreferences.getDataTimestamp()
        val previousCityName = sharedPreferences.getPreviousCityName()
        val cityName = sharedPreferences.getCityName()

        val timestamp = System.currentTimeMillis()
        val currentHour = TimeUnit.MILLISECONDS.toHours(timestamp)
        val previousHour = TimeUnit.MILLISECONDS.toHours(dataTimestamp)

        var weather: Weather? = null

        if (previousCityName == null){
            weather = oneCall(lat, lon)
        }else if (previousCityName.compareTo(cityName!!) != 0 || currentHour != previousHour) {
            weather = oneCall(lat, lon)
        }

        sharedPreferences.setPreviousCityName(cityName!!)
        sharedPreferences.setDataTimestamp(timestamp)

        return weather
    }

    private suspend fun oneCall(lat: Double?, lon: Double?): Weather?{
        var weather: Weather? = null
        val sharedPreferences = SharedPrefsProviderImpl(context)

        val service = createApiService(baseUrl, ForecastService::class.java)
        service.oneCallWeather(lat, lon, units, exclude, API_KEY).await().run {

            val UNIX_TO_JAVA = 1000
            sharedPreferences.setDataTimestamp(this.current.dt.toLong() * UNIX_TO_JAVA)

            val current = Current(
                1, this.current.temp,
                this.current.humidity,
                this.current.clouds,
                this.current.dt.toLong() * UNIX_TO_JAVA,
                this.current.weather[0].icon,
                this.current.weather[0].description
            )

            val listOfHourly: ArrayList<Hourly> = ArrayList()
            val hIterator = this.hourly.iterator()
            var i = -1
            hIterator.forEach {
                // skipping first one because it's current hour
                if (i > -1) {
                    val hourly = Hourly(
                        i,
                        it.temp,
                        it.humidity,
                        it.clouds,
                        it.dt.toLong() * UNIX_TO_JAVA,
                        it.weather[0].icon
                    )
                    listOfHourly.add(hourly)
                }
                i++
            }

            val listOfDaily: ArrayList<Daily> = ArrayList()
            val dIterator = this.daily.iterator()
            i = -1
            dIterator.forEach {
                // skipping first one because it's current day
                if (i > -1) {
                    val daily = Daily(
                        i,
                        it.temp.day,
                        it.temp.min,
                        it.temp.max,
                        it.humidity,
                        it.clouds,
                        it.dt.toLong() * UNIX_TO_JAVA,
                        it.weather[0].icon
                    )
                    listOfDaily.add(daily)
                }
                i++
            }

            weather = Weather(current, listOfHourly, listOfDaily)
        }
        return weather
    }
}