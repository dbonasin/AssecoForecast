package hr.rma.db.assecoforecast.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun<API_SERVICE_TYPE> createApiService(baseUrl: String, apiServiceClass: Class<API_SERVICE_TYPE>): API_SERVICE_TYPE {

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(apiServiceClass)
}