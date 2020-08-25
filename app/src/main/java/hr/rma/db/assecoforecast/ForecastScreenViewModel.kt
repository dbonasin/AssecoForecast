package hr.rma.db.assecoforecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import hr.rma.db.assecoforecast.data.models.Daily
import hr.rma.db.assecoforecast.data.models.Hourly
import hr.rma.db.assecoforecast.data.repository.ForecastRepository


class ForecastScreenViewModel(application: Application) : AndroidViewModel(application) {
    val TAG = "ViewModel"

    private var repository: ForecastRepository = ForecastRepository(application)

    fun getHourly(): LiveData<List<Hourly?>?>?{
        return repository.getHourly()
    }

    fun getDaily(): LiveData<List<Daily?>?>?{
        return repository.getDaily()
    }
}
