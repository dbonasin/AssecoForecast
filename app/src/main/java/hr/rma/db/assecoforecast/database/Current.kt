package hr.rma.db.assecoforecast.database

import androidx.room.*

@Entity(tableName = "current")
open class Current(id : Int,currentTemp : Double, currentHumidity : Double, clouds : Double) {
    @PrimaryKey(autoGenerate = false)
    var id = 0
    @ColumnInfo(name = "current_temp")
    var currentTemp : Double = 0.0
    @ColumnInfo(name = "current_tumidity")
    var currentHumidity : Double = 0.0
    @ColumnInfo(name = "clouds")
    var clouds : Double = 0.0

    init {
        this.id = id
        this.currentTemp = currentTemp
        this.currentHumidity = currentHumidity
        this.clouds = clouds
    }

}