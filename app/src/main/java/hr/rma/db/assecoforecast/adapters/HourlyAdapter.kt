package hr.rma.db.assecoforecast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import hr.rma.db.assecoforecast.R
import hr.rma.db.assecoforecast.database.Hourly
import java.sql.Timestamp
import java.text.SimpleDateFormat
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
        var tvHour: TextView
        var tvTemperature: TextView
        var tvHumidity: TextView
        var tvMaxTemp: TextView
        var tvMinTemp: TextView
        var ivWeather: ImageView
        var ivTemperature: ImageView
        var ivHumidity: ImageView
        var ivMaxTemp: ImageView
        var ivMinTemp: ImageView

        fun bind(hourly: Hourly?) {
            val outFormat = SimpleDateFormat("HH")
            val dayFormat = SimpleDateFormat("EEEE")

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)

            val imageURL = "http://openweathermap.org/img/wn/" + hourly?.icon + ".png"

            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(itemView.context).load(imageURL).apply(options)
                .placeholder(circularProgressDrawable)
                .into(ivWeather)

            val UNIX_TO_JAVA = 1000
            val ts: Date = Timestamp(hourly?.dt!!.toLong() * UNIX_TO_JAVA)
            val today = dayFormat.format(Timestamp(System.currentTimeMillis()))
            val hour = outFormat.format(ts)
            var day = dayFormat.format(ts)

            if (day == today) day = "Today"

            val strHour = day +"\n" + hour + "h"
            val strTmp = hourly.hourTemp.toString() + "°C"
            val strHum = hourly.hourHumidity.toString() + "%"
            tvHour.text = strHour
            tvTemperature.text = strTmp
            tvHumidity.text = strHum

        }

        init {
            tvHour = itemView.findViewById(R.id.tv_day_hour)
            tvTemperature = itemView.findViewById(R.id.tv_estimated_temperature)
            tvHumidity = itemView.findViewById(R.id.tv_humidity)
            tvMaxTemp = itemView.findViewById(R.id.tv_max_temp)
            tvMinTemp = itemView.findViewById(R.id.tv_min_temp)
            ivWeather = itemView.findViewById(R.id.iv_card_weather_icon)
            ivTemperature = itemView.findViewById(R.id.iv_temperature)
            ivHumidity = itemView.findViewById(R.id.iv_humidity)
            ivMaxTemp = itemView.findViewById(R.id.iv_max_temperature)
            ivMinTemp = itemView.findViewById(R.id.iv_min_temperature)

            tvMaxTemp.visibility = View.GONE
            tvMinTemp.visibility = View.GONE
            ivMaxTemp.visibility = View.GONE
            ivMinTemp.visibility = View.GONE
        }
    }

    companion object {
        private const val TAG = "HourlyAdapter"
    }
}