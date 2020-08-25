package hr.rma.db.assecoforecast.data.network

import hr.rma.db.assecoforecast.data.models.Weather
import  hr.rma.db.assecoforecast.data.response.WeatherResponse

interface ForecastNetworkDataSource {
    suspend fun getForecast() : Weather?
}