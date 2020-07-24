package hr.rma.db.assecoforecast.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Query("SELECT * FROM cities WHERE continent LIKE 'Europe'")
    fun getAllCities(): LiveData<List<City>>

    @Query("SELECT * FROM cities WHERE capital_name = :cityName")
    fun getLanAndLon(cityName: String): LiveData<City>

    @Insert
    fun insertMessage(city: City?)
}