package hr.rma.db.assecoforecast.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily")
class Daily(day : Int, dayTemp : Double, minTemp : Double, maxTemp : Double, dayHumidity : Double, clouds : Double, dt: Double, icon: String) {
    @PrimaryKey(autoGenerate = false)
    var day : Int = 0
    @ColumnInfo(name = "current_temp")
    var dayTemp : Double = 0.0
    @ColumnInfo(name = "min_temp")
    var minTemp : Double = 0.0
    @ColumnInfo(name = "max_temp")
    var maxTemp : Double = 0.0
    @ColumnInfo(name = "current_tumidity")
    var dayHumidity : Double = 0.0
    @ColumnInfo(name = "clouds")
    var clouds : Double = 0.0

    //    Date
    @ColumnInfo(name = "date")
    var dt: Double? = null

    //    weather icon
    @ColumnInfo(name = "icon")
    var icon: String? = null

    init {
        this.day = day
        this.dayTemp = dayTemp
        this.minTemp = minTemp
        this.maxTemp = maxTemp
        this.dayHumidity = dayHumidity
        this.clouds = clouds
        this.dt = dt
        this.icon = icon
    }
}