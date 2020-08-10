package hr.rma.db.assecoforecast

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.GsonBuilder
import hr.rma.db.assecoforecast.database.*
import hr.rma.db.assecoforecast.database.Current
import hr.rma.db.assecoforecast.database.Daily
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
    val units = "metric"
    val TAG = "ViewModel"

    private var repository: ForecastRepository? = null
    private var cityRepository: CityRepository? = null

     var hasNetwork: MutableLiveData<Boolean> = MutableLiveData()

    init{
        hasNetwork.value = true
        repository = ForecastRepository(application)
        cityRepository = CityRepository(application)
    }

    fun getLatAndLon(cityName: String) : LiveData<City>? {
        Log.d(TAG, "ušao sam u getLatAndLon u ViewModelu ")
        return cityRepository?.getLatAndLon(cityName)
    }

    fun getAllCities() =  cityRepository?.getAllCities()

    fun getCurrentWeatherCount() = repository?.getCurrentWeatherCount()

    fun getCurrent(): LiveData<Current?>? {
        return repository?.getCurrent()
    }

    fun getHourly(): LiveData<List<Hourly?>?>?{
        return repository?.getHourly()
    }

    fun getDaily(): LiveData<List<Daily?>?>?{
        return repository?.getDaily()
    }

    fun searchCities(cityName: String) = cityRepository?.searchCities(cityName)

    fun getData(){
        val sharedPreferences : SharedPreferences = getApplication<Application>().getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
        val lat = sharedPreferences.getString("CITY_LAT", null)?.toDouble()
        val lon = sharedPreferences.getString("CITY_LON", null)?.toDouble()
        val isEmpty = sharedPreferences.getBoolean("IS_EMPTY", true)

        Log.d(TAG, "Zemljopisna Širina" + lat.toString() + " i dužina:" + lon.toString())

        var weatherResponse : WeatherResponse? = null

        Log.d(TAG, "hasnetwork = " + hasNetwork.value)

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
                    hasNetwork.value = true
                    weatherResponse = response.body()!!
                    Log.d(TAG, "Trenutna temperatura " + weatherResponse?.current?.temp)
                    Log.d(TAG, "Opis " + weatherResponse?.current?.weather?.get(0)!!.description)
                    Log.d(TAG, "Vlaga " + weatherResponse?.current?.humidity)


                    val current = Current(1,response.body()!!.current.temp,
                        response.body()!!.current.humidity,
                        response.body()!!.current.clouds,
                        response.body()!!.current.dt,
                        response.body()!!.current.weather[0].icon,
                        response.body()!!.current.weather[0].description)


                    val listOfHourly :ArrayList<Hourly>? = ArrayList()
                    val hIterator = response.body()!!.hourly.iterator()
                    var i = -1
                    hIterator.forEach {
                        if (i > -1) {
                            val hourly = Hourly(
                                i,
                                it.temp,
                                it.humidity,
                                it.clouds,
                                it.dt,
                                it.weather[0].icon
                            )
                            listOfHourly?.add(hourly)
//                            Log.d(TAG, listOfHourly?.get(i)?.hourTemp.toString())
                        }
                        i++

                    }

                    val listOfDaily: ArrayList<Daily>? = ArrayList()
                    val dIterator = response.body()!!.daily.iterator()
                    i = -1
                    dIterator.forEach {
                        if (i > -1) {
                            val daily = Daily(
                                i,
                                it.temp.day,
                                it.temp.min,
                                it.temp.max,
                                it.humidity,
                                it.clouds,
                                it.dt,
                                it.weather[0].icon
                            )
                            listOfDaily?.add(daily)
                        }
                        i++
                    }

//                    insertimg or upadateing database
                    if ( isEmpty){
                        repository?.insertCurrent(current)
                        listOfHourly?.iterator()?.forEach {
                            repository?.insertHourly(it)
                        }
                        listOfDaily?.iterator()?.forEach {
                            repository?.insertDaily(it)
                        }
                        val editor = sharedPreferences.edit()
                        editor?.putBoolean("IS_EMPTY", false)
                        editor?.apply()
                    } else {
                        repository?.updateCurrent(current)
                        listOfHourly?.iterator()?.forEach {
                            repository?.updateHourly(it)
                        }
                        listOfDaily?.iterator()?.forEach {
                            repository?.updateDaily(it)
                        }
                    }
                } else {
                    Log.d(TAG, "Response code: " + response.code())
                    Log.d(TAG, "URL: api.openweathermap.org/data/2.5/onecall?lat=$lat&lon=$lon&units=$units&exclude=minutely&appid=$API_KEY")
                    hasNetwork.value = false
                }
            }

            override fun onFailure(call: Call<WeatherResponse?>, t: Throwable) {
                hasNetwork.value = false
                Log.d(TAG, "Nisam uspio dobiti json " + t.cause + "\nhasnetwork = " + hasNetwork.value)
            }
        })
    }

    fun makeForecastBlank(){
        repository?.deleteCurrent()
        repository?.deleteHourly()
        repository?.deleteDaily()
    }

}
