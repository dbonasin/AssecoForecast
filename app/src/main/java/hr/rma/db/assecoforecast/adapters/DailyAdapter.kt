package hr.rma.db.assecoforecast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import hr.rma.db.assecoforecast.R
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import hr.rma.db.assecoforecast.data.models.Daily
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class DailyAdapter : RecyclerView.Adapter<DailyAdapter.DailyWeatherHolder?>() {

        private var DailyList: List<Daily?>? = ArrayList()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): DailyWeatherHolder {
            val view: View
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_item, parent, false)
            return DailyWeatherHolder(view)
        }

         override fun onBindViewHolder(holder: DailyWeatherHolder, position: Int) {
             val daily: Daily? = DailyList!![position]
             holder.bind(daily)
         }

        override fun getItemCount(): Int {
            return if (DailyList == null) 0 else DailyList!!.size
        }

        fun setWeather(daily: List<Daily?>?) {
            DailyList = daily
            notifyDataSetChanged()
        }

        class DailyWeatherHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView){
            private val tvNameOfDay: TextView? = itemView.findViewById(R.id.tv_day_hour)
            private val tvTemperature: TextView = itemView.findViewById(R.id.tv_estimated_temperature)
            private val tvHumidity: TextView = itemView.findViewById(R.id.tv_humidity)
            private val tvMaxTemp: TextView = itemView.findViewById(R.id.tv_max_temp)
            private val tvMinTemp: TextView = itemView.findViewById(R.id.tv_min_temp)
            private val ivWeather: ImageView = itemView.findViewById(R.id.iv_card_weather_icon)


            fun bind(daily: Daily?) {
                val outFormat = SimpleDateFormat("EEEE")
                val ts: Date = Timestamp(daily?.dt!!)
                val day = outFormat.format(ts)

                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)

                val imageURL = "http://openweathermap.org/img/wn/" + daily.icon + ".png"

                val circularProgressDrawable = CircularProgressDrawable(itemView.context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()

                Glide.with(itemView.context).load(imageURL).apply(options)
                    .placeholder(circularProgressDrawable)
                    .into(ivWeather)

                tvNameOfDay?.text = day
                var strTmp = daily.dayTemp.toString() + "°C"
                val strHum = daily.dayHumidity.toString() + "%"
                tvTemperature.text = strTmp
                tvHumidity.text = strHum
                strTmp = daily.maxTemp.toString() + "°C"
                tvMaxTemp.text = strTmp
                strTmp = daily.minTemp.toString() + "°C"
                tvMinTemp.text = strTmp
            }
        }

        companion object {
            private const val TAG = "DailyAdapter"
        }
}
