package hr.rma.db.assecoforecast.database

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData

//TODO napraviti te asyncTaskove da može razgovarati s bazom
class ForecastRepository(application: Application) {
    private val TAG : String  = "ForecastRepository"

    private var forecastDao: ForecastDao? = null
//
    init{
        val database: ForecastDatabase? = ForecastDatabase.getInstance(application)
        forecastDao = database?.forecastDao()
    }

    fun insertCurrent(current: Current) {
        Log.d(TAG,"Trenutno vrijeme se inserta u bazu: " + current.currentTemp)
        InsertCurrentAsyncTask(forecastDao).execute(current)
    }
    fun insertHourly(hourly: Hourly) {
        Log.d(TAG,"hourly vrijeme se inserta u bazu: ")
        InsertHourlyAsyncTask(forecastDao).execute(hourly)
    }
    fun insertDaily(daily: Daily) {
        Log.d(TAG,"daily vrijeme se inserta u bazu: ")
        InsertDailyAsyncTask(forecastDao).execute(daily)
    }

    fun updateCurrent(current: Current) {
        Log.d(TAG,"Trenutno vrijeme se updatea u bazu: " + current.currentTemp)
        UpdateCurrentAsyncTask(forecastDao).execute(current)
    }
    fun updateHourly(hourly: Hourly) {
        Log.d(TAG,"hourly vrijeme se updatea u bazu: ")
        UpdateHourlyAsyncTask(forecastDao).execute(hourly)
    }
    fun updateDaily(daily: Daily) {
        Log.d(TAG,"daily vrijeme se updatea u bazu: ")
        UpdateDailyAsyncTask(forecastDao).execute(daily)
    }

    fun getCurrentWeatherCount(): LiveData<Int>? = forecastDao?.getCurrentWeatherCount()

    fun getCurrent(): LiveData<Current?>? = forecastDao?.getCurrentWeather();

    fun getHourly(): LiveData<List<Hourly?>?>? = forecastDao?.getHourlyWeather();

    fun getDaily(): LiveData<List<Daily?>?>? = forecastDao?.getDailyWeather();
//
    private class InsertCurrentAsyncTask (forecastDao: ForecastDao?) : AsyncTask<Current?, Void?, Void?>() {
        var forecastDao: ForecastDao? = null
            init {
                this.forecastDao = forecastDao
            }

        override fun doInBackground(vararg params: Current?): Void? {
            forecastDao?.insertCurrentWeather(params[0])
            return null
        }
    }

    private class InsertHourlyAsyncTask (forecastDao: ForecastDao?) : AsyncTask<Hourly?, Void?, Void?>() {
        var forecastDao: ForecastDao? = null
        init {
            this.forecastDao = forecastDao
        }

        override fun doInBackground(vararg params: Hourly?): Void? {
            forecastDao?.insertHourlyWeather(params[0])
            return null
        }
    }

    private class InsertDailyAsyncTask (forecastDao: ForecastDao?) : AsyncTask<Daily?, Void?, Void?>() {
        var forecastDao: ForecastDao? = null
        init {
            this.forecastDao = forecastDao
        }

        override fun doInBackground(vararg params: Daily?): Void? {
            forecastDao?.insertDailyWeather(params[0])
            return null
        }
    }

    private class UpdateCurrentAsyncTask (forecastDao: ForecastDao?) : AsyncTask<Current?, Void?, Void?>() {
        var forecastDao: ForecastDao? = null
        init {
            this.forecastDao = forecastDao
        }

        override fun doInBackground(vararg params: Current?): Void? {
            forecastDao?.updateCurrentWeather(params[0])
            return null
        }
    }

    private class UpdateHourlyAsyncTask (forecastDao: ForecastDao?) : AsyncTask<Hourly?, Void?, Void?>() {
        var forecastDao: ForecastDao? = null
        init {
            this.forecastDao = forecastDao
        }

        override fun doInBackground(vararg params: Hourly?): Void? {
            forecastDao?.updateHourlyWeather(params[0])
            return null
        }
    }

    private class UpdateDailyAsyncTask (forecastDao: ForecastDao?) : AsyncTask<Daily?, Void?, Void?>() {
        var forecastDao: ForecastDao? = null
        init {
            this.forecastDao = forecastDao
        }

        override fun doInBackground(vararg params: Daily?): Void? {
            forecastDao?.updateDailyWeather(params[0])
            return null
        }
    }
}