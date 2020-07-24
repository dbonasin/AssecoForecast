package hr.rma.db.assecoforecast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.rma.db.assecoforecast.R
import hr.rma.db.assecoforecast.database.Hourly
import java.util.*

class HourlyAdapter : RecyclerView.Adapter<HourlyAdapter.HourlyWeatherHolder>() {

    private var HourlyList: List<Hourly>? = ArrayList<Hourly>()

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
        val hourly: Hourly = HourlyList!![position]
        holder.bind(hourly)
    }

    override fun getItemCount(): Int {
        return if (HourlyList == null) 0 else HourlyList!!.size
    }

    fun setWeather(hourly: List<Hourly>?) {
        HourlyList = hourly
        notifyDataSetChanged()
    }

    class HourlyWeatherHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        //ovdje inicijalizirati textviewove
        fun bind(hourly: Hourly) {
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
        private const val TAG = "HourlyAdapter"
    }
}