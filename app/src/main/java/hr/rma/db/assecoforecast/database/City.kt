package hr.rma.db.assecoforecast.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["longitude", "latitude"], tableName = "cities")
class City {
    @ColumnInfo(name = "country")
    @SerializedName("CountryName")
    @Expose
    var countryName: String? = null

    @ColumnInfo(name = "capital_name")
    @SerializedName("CapitalName")
    @Expose
    var capitalName: String? = null

    @ColumnInfo(name = "latitude")
    @SerializedName("CapitalLatitude")
    @Expose
    var capitalLatitude: String? = null

    @ColumnInfo(name = "longitude")
    @SerializedName("CapitalLongitude")
    @Expose
    var capitalLongitude: String? = null

    @ColumnInfo(name = "country_code")
    @SerializedName("CountryCode")
    @Expose
    var countryCode: String? = null

    @ColumnInfo(name = "continent")
    @SerializedName("ContinentName")
    @Expose
    var continentName: String? = null
}