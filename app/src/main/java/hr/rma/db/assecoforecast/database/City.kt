package hr.rma.db.assecoforecast.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(primaryKeys = ["CapitalLatitude", "CapitalLongitude"], tableName = "cities")
class City {
    @ColumnInfo(name = "CountryName")
    @SerializedName("CountryName")
    @Expose
    var countryName: String? = null

    @ColumnInfo(name = "CapitalName")
    @SerializedName("CapitalName")
    @Expose
    var capitalName: String? = null

    @ColumnInfo(name = "CapitalLatitude")
    @SerializedName("CapitalLatitude")
    @Expose
    @NotNull
    var capitalLatitude: String? = null

    @ColumnInfo(name = "CapitalLongitude")
    @SerializedName("CapitalLongitude")
    @Expose
    @NotNull
    var capitalLongitude: String? = null

    @ColumnInfo(name = "CountryCode")
    @SerializedName("CountryCode")
    @Expose
    var countryCode: String? = null

    @ColumnInfo(name = "ContinentName")
    @SerializedName("ContinentName")
    @Expose
    var continentName: String? = null
}