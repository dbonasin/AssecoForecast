package hr.rma.db.assecoforecast.data.provider

interface SharedPrefsProvider {

    fun getLat(): String?

    fun getLon(): String?

    fun getCityName(): String?

    fun getDataTimestamp(): Long

    fun getPreviousCityName(): String?

    fun setLat(lat: String)

    fun setLon(lon: String)

    fun setCityName(cityName: String)

    fun setDataTimestamp(dataTimestamp: Long)

    fun setPreviousCityName(cityName: String)
}