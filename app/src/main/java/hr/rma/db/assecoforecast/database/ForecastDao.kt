package hr.rma.db.assecoforecast.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ForecastDao {
    @Query("SELECT COUNT(*) FROM daily")
    fun getCurrentweatherCount() :LiveData<Int>

    @Query("SELECT * FROM current")
    fun getCurrentWeather(): LiveData<Current?>?

    @Query("SELECT * FROM daily")
    fun getDailyWeather(): LiveData<List<Daily?>?>?

    @Query("SELECT * FROM hourly")
    fun getHourlyWeather(): LiveData<List<Hourly?>?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCurrentWeather(weather: Current?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDailyWeather(vararg weather: Daily?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHourlyWeather(vararg weather: Hourly?)

}