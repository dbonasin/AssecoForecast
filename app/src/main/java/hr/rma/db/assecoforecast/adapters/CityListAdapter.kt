package hr.rma.db.assecoforecast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.rma.db.assecoforecast.database.City
import hr.rma.db.assecoforecast.R
import java.util.*

class CityListAdapter(listener: ListItemClickListener) : RecyclerView.Adapter<CityListAdapter.CityListHolder>() {

    private var CityList: List<City>? = ArrayList<City>()
    private var mOnClickListener: ListItemClickListener? =
        null

    init{
        mOnClickListener = listener
    }

    interface ListItemClickListener {
        fun onListItemClick(clickedItemIndex: Int)
    }

//        override fun getItemViewType(position: Int): Int {
//            val city: City = CityList!![position]
//            return if (city.getSenderID().equals(mUsrName)) {
//                VIEW_TYPE_MESSAGE_SENT
//            } else {
//                VIEW_TYPE_MESSAGE_RECEIVED
//            }
//        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityListHolder {
        val view: View
        view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false)
        return CityListHolder(view)
    }

    override fun onBindViewHolder(holder: CityListHolder, position: Int) {
        val city: City = CityList!![position]
        holder.bind(city)
    }

    override fun getItemCount(): Int {
        return if (CityList == null) 0 else CityList!!.size
    }

    fun setWeather(city: List<City>?) {
        CityList = city
        notifyDataSetChanged()
    }

    inner class CityListHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        //ovdje inicijalizirati textviewove
        fun bind(city: City) {
            //ovdje staviti što treba pisati u text viewu
        }

        init {
            //Ovdje naći i staviti textviewove u samom itemu
//                tvMsgRec = itemView.findViewById(R.id.tv_rec_message_body)
//                tvTimeMsgRec = itemView.findViewById(R.id.tv_rec_message_time)
        }

        override fun onClick(v: View?) {
            val clickedPosition = adapterPosition
            mOnClickListener?.onListItemClick(clickedPosition)
        }
    }

    companion object {
        private const val TAG = "CityAdapter"
    }
}