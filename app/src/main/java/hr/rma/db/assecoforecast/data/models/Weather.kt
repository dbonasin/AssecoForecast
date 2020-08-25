package hr.rma.db.assecoforecast.data.models

import hr.rma.db.assecoforecast.data.models.Current
import hr.rma.db.assecoforecast.data.models.Daily
import hr.rma.db.assecoforecast.data.models.Hourly

class Weather(current: Current, hourly: List<Hourly>, daily: List<Daily>) {

    var current: Current? = null
    var hourly: List<Hourly>? = null
    var daily: List<Daily>? = null

    init {
        this.current = current
        this.hourly = hourly
        this.daily = daily
    }
}