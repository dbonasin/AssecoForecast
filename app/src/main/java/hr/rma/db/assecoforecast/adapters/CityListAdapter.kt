package hr.rma.db.assecoforecast.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import hr.rma.db.assecoforecast.database.City
import hr.rma.db.assecoforecast.R
import java.util.*

class CityListAdapter(listener: ListItemClickListener) : RecyclerView.Adapter<CityListAdapter.CityListHolder>() {

    private var CityList: List<City?>? = ArrayList()
    private var mOnClickListener: ListItemClickListener = listener

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
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false)
        return CityListHolder(view)
    }

    override fun onBindViewHolder(holder: CityListHolder, position: Int) {
        val city: City? = CityList!![position]
        holder.bind(city!!)
//        holder.itemView.setOnClickListener {
//            object : View.OnClickListener {
//                override fun onClick(v: View?) {
//                Toast.makeText(v?.context, "Kliko si me", Toast.LENGTH_SHORT).show()
//                    Log.d(TAG, "kliknuo si na mene")
//                }
//
//            }
//        }
    }

    override fun getItemCount(): Int {
        return if (CityList == null) 0 else CityList!!.size
    }

    fun setCities(city: List<City?>?) {
        CityList = city
        notifyDataSetChanged()
    }

    inner class CityListHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private var tvNameOfCity: TextView? = null

        fun bind(city: City) {
            tvNameOfCity?.text = city.capitalName
        }

        init {
            itemView.setOnClickListener(this)
            tvNameOfCity = itemView.findViewById(R.id.list_city_name)
        }
//
        override fun onClick(v: View?) {
            val clickedPosition = adapterPosition
//            Log.d("SearchAdapter", "Kliknuo si")
            mOnClickListener.onListItemClick(clickedPosition)
        }
    }

    companion object {
        private const val TAG = "CityAdapter"
    }
}