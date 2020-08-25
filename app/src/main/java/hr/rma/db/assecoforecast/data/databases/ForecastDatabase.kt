package hr.rma.db.assecoforecast.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.rma.db.assecoforecast.data.dao.ForecastDao
import hr.rma.db.assecoforecast.data.models.Current
import hr.rma.db.assecoforecast.data.models.Daily
import hr.rma.db.assecoforecast.data.models.Hourly

@Database(entities = [Current::class, Daily::class, Hourly::class], version = 1, exportSchema = false)
abstract class ForecastDatabase : RoomDatabase(){
    abstract fun forecastDao(): ForecastDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: ForecastDatabase

        fun getInstance(context: Context): ForecastDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
            synchronized(this) {
                if (!Companion::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ForecastDatabase::class.java,
                        "forecast_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
