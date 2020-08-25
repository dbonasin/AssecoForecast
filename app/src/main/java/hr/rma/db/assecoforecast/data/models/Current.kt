package hr.rma.db.assecoforecast.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current")
class Current(id : Int,currentTemp : Double, currentHumidity : Double, clouds : Double, dt: Long, icon: String, description: String) {
    @PrimaryKey(autoGenerate = false)
    var id = 0
    @ColumnInfo(name = "current_temp")
    var currentTemp : Double = 0.0
    @ColumnInfo(name = "current_tumidity")
    var currentHumidity : Double = 0.0
    @ColumnInfo(name = "clouds")
    var clouds : Double = 0.0

//    Date
    @ColumnInfo(name = "date")
    var dt: Long? = null

//    weather icon
    @ColumnInfo(name = "icon")
    var icon: String? = null

    @ColumnInfo(name = "description")
    var description: String? = null


    init {
        this.id = id
        this.currentTemp = currentTemp
        this.currentHumidity = currentHumidity
        this.clouds = clouds
        this.dt = dt
        this.icon = icon
        this.description = description
    }

}