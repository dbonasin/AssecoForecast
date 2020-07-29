package hr.rma.db.assecoforecast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import hr.rma.db.assecoforecast.R
import hr.rma.db.assecoforecast.database.Hourly
import java.util.*

class HourlyAdapter : RecyclerView.Adapter<HourlyAdapter.HourlyWeatherHolder>() {

    private var HourlyList: List<Hourly?>? = ArrayList()

//        override fun getItemViewType(position: Int): Int {
//            val hourly: Hourly = HourlyList!![position]
//            return if (hourly.getSenderID().equals(mUsrName)) {
//                VIEW_TYPE_MESSAGE_SENT
//            } else {
//                VIEW_TYPE_MESSAGE_RECEIVED
//            }
//        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourlyWeatherHolder {
        val view: View
        view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)
        return HourlyWeatherHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyWeatherHolder, position: Int) {
        val hourly: Hourly? = HourlyList!![position]
        holder.bind(hourly)
    }

    override fun getItemCount(): Int {
        return if (HourlyList == null) 0 else HourlyList!!.size
    }

    fun setWeather(hourly: List<Hourly?>?) {
        HourlyList = hourly
        notifyDataSetChanged()
    }

    class HourlyWeatherHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        //ovdje inicijalizirati textviewove
        var tvHour: TextView? = null
        var tvTemperature: TextView? = null
        var tvHumidity: TextView? = null
        var tvMaxTemp: TextView? = null
        var tvMinTemp: TextView? = null

        fun bind(hourly: Hourly?) {
            val strHour = (hourly!!.hour + 1).toString() + "h"
            val strTmp = hourly.hourTemp.toString() + "°C"
            val strHum = hourly.hourHumidity.toString() + "%"
            tvHour?.text = strHour
            tvTemperature?.text = strTmp
            tvHumidity?.text = strHum
            tvMaxTemp?.visibility = View.GONE
            tvMinTemp?.visibility = View.GONE
        }

        init {
            tvHour = itemView.findViewById(R.id.tv_day_hour)
            tvTemperature = itemView.findViewById(R.id.tv_estimated_temperature)
            tvHumidity = itemView.findViewById(R.id.tv_humidity)
            tvMaxTemp = itemView.findViewById(R.id.tv_max_temp)
            tvMinTemp = itemView.findViewById(R.id.tv_min_temp)
        }
    }

    companion object {
        private const val TAG = "HourlyAdapter"
    }
}