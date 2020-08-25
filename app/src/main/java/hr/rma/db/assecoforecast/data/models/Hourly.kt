package hr.rma.db.assecoforecast.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hourly")
class Hourly (hour : Int, hourTemp : Double, hourHumidity : Double, clouds : Double, dt: Long, icon: String){
    @PrimaryKey(autoGenerate = false)
    var hour : Int = 0
    @ColumnInfo(name = "current_temp")
    var hourTemp : Double = 0.0
    @ColumnInfo(name = "current_tumidity")
    var hourHumidity : Double = 0.0
    @ColumnInfo(name = "clouds")
    var clouds : Double = 0.0

    //    Date
    @ColumnInfo(name = "date")
    var dt: Long? = null

    //    weather icon
    @ColumnInfo(name = "icon")
    var icon: String? = null


    init {
        this.hour = hour
        this.hourTemp = hourTemp
        this.hourHumidity = hourHumidity
        this.clouds = clouds
        this.dt = dt
        this.icon = icon
    }
}