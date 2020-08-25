package hr.rma.db.assecoforecast.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.rma.db.assecoforecast.data.models.Current
import hr.rma.db.assecoforecast.data.models.Daily
import hr.rma.db.assecoforecast.data.models.Hourly


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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(vararg weather: Current)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(vararg weather: Daily)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourlyWeather(vararg weather: Hourly)

    @Query("DELETE FROM current" )
    fun deleteCurrent()

    @Query("DELETE FROM daily" )
    fun deleteDaily()

    @Query("DELETE FROM hourly" )
    fun deleteHourly()

}