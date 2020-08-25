package hr.rma.db.assecoforecast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.rma.db.assecoforecast.data.models.City
import hr.rma.db.assecoforecast.R
import java.util.*

class CityListAdapter(listener: ListItemClickListener) : RecyclerView.Adapter<CityListAdapter.CityListHolder>() {

    private var cityList: List<City?>? = ArrayList()
    private val mOnClickListener: ListItemClickListener = listener

    interface ListItemClickListener {
        fun onListItemClick(clickedCityName: String)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityListHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item, parent, false)
        return CityListHolder(view)
    }

    override fun onBindViewHolder(holder: CityListHolder, position: Int) {
        val city: City? = cityList!![position]
        holder.bind(city!!)
    }

    override fun getItemCount(): Int {
        return if (cityList == null) 0 else cityList!!.size
    }

    fun setCities(city: List<City?>?) {
        cityList = city
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
            mOnClickListener.onListItemClick(tvNameOfCity?.text.toString())
        }
    }

    companion object {
        private const val TAG = "CityAdapter"
    }
}