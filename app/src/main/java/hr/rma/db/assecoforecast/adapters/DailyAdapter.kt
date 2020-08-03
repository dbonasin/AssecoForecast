package hr.rma.db.assecoforecast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import hr.rma.db.assecoforecast.R
import hr.rma.db.assecoforecast.database.Daily
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class DailyAdapter : RecyclerView.Adapter<DailyAdapter.DailyWeatherHolder?>() {

        private var DailyList: List<Daily?>? = ArrayList()

//        override fun getItemViewType(position: Int): Int {
//            val daily: Daily = DailyList!![position]
//            return if (daily.getSenderID().equals(mUsrName)) {
//                VIEW_TYPE_MESSAGE_SENT
//            } else {
//                VIEW_TYPE_MESSAGE_RECEIVED
//            }
//        }

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
            var tvNameOfDay: TextView? = null
            var tvTemperature: TextView? = null
            var tvHumidity: TextView? = null
            var tvMaxTemp: TextView? = null
            var tvMinTemp: TextView? = null
            var ivWeather: ImageView? = null

            fun bind(daily: Daily?) {
                val UNIX_TO_JAVA = 1000
                val outFormat = SimpleDateFormat("EEEE")
                val ts: Date = Timestamp(daily?.dt!!.toLong() * UNIX_TO_JAVA)
                val day = outFormat.format(ts)

                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)

                val imageURL = "http://openweathermap.org/img/wn/" + daily.icon + ".png"

                Glide.with(itemView.context).load(imageURL).apply(options)
                    .into(ivWeather!!)

                tvNameOfDay?.text = day
                var strTmp = daily.dayTemp.toString() + "°C"
                val strHum = daily.dayHumidity.toString() + "%"
                tvTemperature?.text = strTmp
                tvHumidity?.text = strHum
                strTmp = "^ " + daily.maxTemp.toString() + "°C"
                tvMaxTemp?.text = strTmp
                strTmp = "˘ " + daily.minTemp.toString() + "°C"
                tvMinTemp?.text = strTmp
            }

            init {
                //Ovdje naći i staviti textviewove u samom itemu
//                tvMsgRec = itemView.findViewById(R.id.tv_rec_message_body)
//                tvTimeMsgRec = itemView.findViewById(R.id.tv_rec_message_time)
                tvNameOfDay = itemView.findViewById(R.id.tv_day_hour)
                tvTemperature = itemView.findViewById(R.id.tv_estimated_temperature)
                tvHumidity = itemView.findViewById(R.id.tv_humidity)
                tvMaxTemp = itemView.findViewById(R.id.tv_max_temp)
                tvMinTemp = itemView.findViewById(R.id.tv_min_temp)
                ivWeather = itemView.findViewById(R.id.iv_card_weather_icon)
            }
        }

        companion object {
            private const val TAG = "DailyAdapter"
        }
}
