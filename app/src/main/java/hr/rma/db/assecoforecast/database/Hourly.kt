package hr.rma.db.assecoforecast.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hourly")
class Hourly (hour : Int, hourTemp : Double, hourHumidity : Double, clouds : Double){
    @PrimaryKey(autoGenerate = false)
    var hour : Int = 0
    @ColumnInfo(name = "current_temp")
    var hourTemp : Double = 0.0
    @ColumnInfo(name = "current_tumidity")
    var hourHumidity : Double = 0.0
    @ColumnInfo(name = "clouds")
    var clouds : Double = 0.0

    init {
        this.hour = hour
        this.hourTemp = hourTemp
        this.hourHumidity = hourHumidity
        this.clouds = clouds
    }
}