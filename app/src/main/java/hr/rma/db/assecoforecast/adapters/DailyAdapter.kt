package hr.rma.db.assecoforecast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.rma.db.assecoforecast.R
import hr.rma.db.assecoforecast.database.Daily
import java.util.*

class DailyAdapter : RecyclerView.Adapter<DailyAdapter.DailyWeatherHolder?>() {

        private var DailyList: List<Daily>? = ArrayList<Daily>()

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
             val daily: Daily = DailyList!![position]
             holder.bind(daily)
         }

        override fun getItemCount(): Int {
            return if (DailyList == null) 0 else DailyList!!.size
        }

        fun setWeather(daily: List<Daily>?) {
            DailyList = daily
            notifyDataSetChanged()
        }

        class DailyWeatherHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView), OnClickListener{
            //ovdje inicijalizirati textviewove
            fun bind(daily: Daily) {
                //ovdje staviti što treba pisati u text viewu
            }

            init {
                //Ovdje naći i staviti textviewove u samom itemu
//                tvMsgRec = itemView.findViewById(R.id.tv_rec_message_body)
//                tvTimeMsgRec = itemView.findViewById(R.id.tv_rec_message_time)
            }

            override fun onClick(v: View?) {
                TODO("Not yet implemented")
            }
        }

        companion object {
            private const val TAG = "DailyAdapter"
        }
}
