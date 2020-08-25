package hr.rma.db.assecoforecast.data.provider

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsProviderImpl(context: Context) : SharedPrefsProvider {

    private val sharedPreferences : SharedPreferences = context.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    override fun getLat(): String? {
        return sharedPreferences.getString("CITY_LAT", null)
    }

    override fun getLon(): String? {
        return sharedPreferences.getString("CITY_LON", null)
    }

    override fun getCityName(): String? {
        return sharedPreferences.getString("CITY_NAME", null)
    }

    override fun getDataTimestamp(): Long {
        return sharedPreferences.getLong("TIMESTAMP", 0)
    }

    override fun getPreviousCityName(): String? {
        return sharedPreferences.getString("PREV_CITY_NAME", null)
    }

    override fun setLat(lat: String){
        editor.putString("CITY_LAT", lat)
        editor?.apply()
    }

    override fun setLon(lon: String) {
        editor.putString("CITY_LON", lon)
        editor?.commit()
    }

    override fun setCityName(cityName: String) {
        editor.putString("CITY_NAME", cityName)
        editor?.commit()
    }

    override fun setDataTimestamp(dataTimestamp: Long) {
        editor.putLong("TIMESTAMP", dataTimestamp)
        editor?.commit()
    }

    override fun setPreviousCityName(cityName: String) {
        editor.putString("PREV_CITY_NAME", cityName)
        editor?.commit()
    }


}