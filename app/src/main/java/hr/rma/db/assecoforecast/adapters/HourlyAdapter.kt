package hr.rma.db.assecoforecast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import hr.rma.db.assecoforecast.R
import hr.rma.db.assecoforecast.data.models.Hourly
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class HourlyAdapter : RecyclerView.Adapter<HourlyAdapter.HourlyWeatherHolder>() {

    private var hourlyList: List<Hourly?>? = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourlyWeatherHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)
        return HourlyWeatherHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyWeatherHolder, position: Int) {
        val hourly: Hourly? = hourlyList!![position]
        holder.bind(hourly)
    }

    override fun getItemCount(): Int {
        return if (hourlyList == null) 0 else hourlyList!!.size
    }

    fun setWeather(hourly: List<Hourly?>?) {
        hourlyList = hourly
        notifyDataSetChanged()
    }

    class HourlyWeatherHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val tvHour: TextView = itemView.findViewById(R.id.tv_day_hour)
        private val tvTemperature: TextView = itemView.findViewById(R.id.tv_estimated_temperature)
        private val tvHumidity: TextView = itemView.findViewById(R.id.tv_humidity)
        private val tvMaxTemp: TextView = itemView.findViewById(R.id.tv_max_temp)
        private val tvMinTemp: TextView = itemView.findViewById(R.id.tv_min_temp)
        private val ivWeather: ImageView = itemView.findViewById(R.id.iv_card_weather_icon)
        private val ivMaxTemp: ImageView = itemView.findViewById(R.id.iv_max_temperature)
        private val ivMinTemp: ImageView = itemView.findViewById(R.id.iv_min_temperature)

        fun bind(hourly: Hourly?) {
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

            val outFormat = SimpleDateFormat("HH")
            val dayFormat = SimpleDateFormat("EEEE")

            val ts: Date = Timestamp(hourly?.dt!!)
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