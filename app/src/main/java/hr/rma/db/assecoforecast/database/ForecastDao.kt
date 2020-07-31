package hr.rma.db.assecoforecast.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ForecastDao {
    @Query("SELECT COUNT(*) FROM daily")
    fun getCurrentWeatherCount() :LiveData<Int>

    @Query("SELECT * FROM current")
    fun getCurrentWeather(): LiveData<Current?>?

    @Query("SELECT * FROM daily")
    fun getDailyWeather(): LiveData<List<Daily?>?>?

    @Query("SELECT * FROM hourly")
    fun getHourlyWeather(): LiveData<List<Hourly?>?>?

    @Insert
    fun insertCurrentWeather(weather: Current?)

    @Insert
    fun insertDailyWeather(vararg weather: Daily?)

    @Insert
    fun insertHourlyWeather(vararg weather: Hourly?)

    @Update
    fun updateCurrentWeather(weather: Current?)

    @Update
    fun updateDailyWeather(vararg weather: Daily?)

    @Update
    fun updateHourlyWeather(vararg weather: Hourly?)

}