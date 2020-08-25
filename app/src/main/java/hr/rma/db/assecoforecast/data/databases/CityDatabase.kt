package hr.rma.db.assecoforecast.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.rma.db.assecoforecast.data.dao.CityDao
import hr.rma.db.assecoforecast.data.models.City

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase(){
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: CityDatabase

        fun getInstance(context: Context): CityDatabase {

            synchronized(this) {
                if (!Companion::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CityDatabase::class.java,
                        "city_database.db"
                    )
                        .createFromAsset("city_database.db")
                        .build()
                }
                return INSTANCE
            }
        }
    }
}