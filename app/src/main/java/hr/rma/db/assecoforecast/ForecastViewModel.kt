package hr.rma.db.assecoforecast

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.gson.GsonBuilder
import hr.rma.db.assecoforecast.database.Current
import hr.rma.db.assecoforecast.database.ForecastRepository
import hr.rma.db.assecoforecast.database.Hourly
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ForecastViewModel(application: Application) : AndroidViewModel(application) {
    //    api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=659e20ae4addd2c6c99d190ee44beca4
//     Zagreb-> 45.8150° N, 15.9819° E
    val API_KEY : String = "659e20ae4addd2c6c99d190ee44beca4"
    var lat = 45.8150;
    var lon = 15.9819;
    var units = "metric"
    val TAG = "ViewModel"

    private var repository: ForecastRepository? = null

    init{
        repository = ForecastRepository(application)
    }

    fun getCurrent(): LiveData<Current?>? {
        return repository?.getCurrent()
    }
    fun getHourly(): LiveData<List<Hourly?>?>?{
        return repository?.getHourly()
    }

    fun getData() : WeatherResponse?{

        var weatherResponse : WeatherResponse? = null

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit: Retrofit? = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val service: OpenWeatherService = retrofit!!.create(OpenWeatherService::class.java)
        val call : Call<WeatherResponse?>? = service.oneCallWeather(lat, lon, units, "minutely", API_KEY)
        call?.enqueue(object : Callback<WeatherResponse?>{

            override fun onResponse(
                call: Call<WeatherResponse?>,
                response: Response<WeatherResponse?>
            ) {
                Log.d(TAG, "Pred preuzimanjem jsona")
                if (response.code() == 200){
                    weatherResponse = response.body()!!
//                  TODO Weather response treba nekako raspakirati i spremiti podatke iz tog jsona
                    Log.d(TAG, "Preuzeo json " + weatherResponse?.current?.feelsLike)
                    val current = Current(1,response.body()!!.current.temp, response.body()!!.current.humidity, response.body()!!.current.clouds)
                    val listOfHourly :ArrayList<Hourly>? = ArrayList()
                    val iterator = response.body()!!.hourly.iterator()
                    var i : Int = 0
                    iterator.forEach {
                        val hourly = Hourly(i, it.temp, it.humidity, it.clouds)
                        listOfHourly?.add(hourly)
                        Log.d(TAG, listOfHourly?.get(i)?.hourTemp.toString())
                        i++

                    }


//                    if(repository!!.isCurrentEmpty()!!.value!!){
                        repository?.insertCurrent(current)
                        listOfHourly?.iterator()?.forEach {
                            repository?.insertHourly(it)

                        }
//
//                    } else {
//                        repository?.updateCurrent(current)
//                        listOfHourly?.iterator()?.forEach {
//                            repository?.updateHourly(it)
//                        }
//                    }

                }
            }

            override fun onFailure(call: Call<WeatherResponse?>, t: Throwable) {
                Log.d(TAG, "Nisam uspio dobiti json " + t.cause)
                getData()

            }
        })
    return weatherResponse
    }


}
