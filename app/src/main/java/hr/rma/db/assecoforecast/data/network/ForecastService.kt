package hr.rma.db.assecoforecast.data.network

import hr.rma.db.assecoforecast.data.response.WeatherResponse
import hr.rma.db.assecoforecast.data.createApiService
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    companion object{
        operator fun invoke() : ForecastService {
            return createApiService("https://api.openweathermap.org/", ForecastService::class.java)
        }
    }

    @GET("data/2.5/onecall?") //lat={lat}&lon={lon}&units=metric&exclude={part}&appid={API_KEY}
    fun oneCallWeather(@Query("lat") lat: Double?, @Query("lon") lon: Double?, @Query("units") units: String?,
                       @Query("exclude") exclude: String?, @Query("appid") API_KEY: String?): Deferred<WeatherResponse>
}