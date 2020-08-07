package hr.rma.db.assecoforecast

import hr.rma.db.assecoforecast.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("data/2.5/onecall?") //lat={lat}&lon={lon}&units=metric&exclude={part}&appid={API_KEY}
    fun oneCallWeather(@Query("lat") lat: Double?, @Query("lon") lon: Double?, @Query("units") units: String?,
                       @Query("exclude") exclude: String?, @Query("appid") API_KEY: String?): Call<WeatherResponse?>?
}