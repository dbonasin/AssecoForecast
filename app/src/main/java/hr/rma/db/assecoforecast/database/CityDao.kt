package hr.rma.db.assecoforecast.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Query("SELECT * FROM cities WHERE ContinentName LIKE 'Europe'")
    fun getAllCities(): LiveData<List<City>>

    @Query("SELECT COUNT(*) FROM cities WHERE ContinentName LIKE 'Europe'")
    fun getNumberOFCities(): LiveData<Int>

    @Query("SELECT * FROM cities WHERE CapitalName LIKE :cityName")
    fun getLatAndLon(cityName: String): LiveData<City>
}