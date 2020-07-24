package hr.rma.db.assecoforecast.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase(){
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var INSTANCE: CityDatabase? = null

        fun getInstance(context: Context): CityDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityDatabase::class.java,
                    "city_database.db"
                )
                    .createFromAsset("city_database.db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}