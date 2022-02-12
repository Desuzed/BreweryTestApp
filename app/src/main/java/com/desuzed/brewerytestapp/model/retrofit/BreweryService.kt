package com.desuzed.brewerytestapp.model.retrofit

import com.desuzed.brewerytestapp.model.retrofit.dto.BreweryDto
import com.desuzed.brewerytestapp.model.retrofit.dto.ErrorRetrofitDto
import com.desuzed.brewerytestapp.model.retrofit.network.NetworkResponse
import com.desuzed.brewerytestapp.model.retrofit.network.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface BreweryService {
    @GET("breweries")
    suspend fun fetchBreweriesList(): NetworkResponse<List<BreweryDto>, ErrorRetrofitDto>

    @GET("breweries/{id}")
    suspend fun fetchBrewery(@Path("id") id: String): NetworkResponse<BreweryDto, ErrorRetrofitDto>

    companion object {
        private const val baseUrl = "https://api.openbrewerydb.org/"
        private var weatherApiService: BreweryService? = null
        fun getInstance(): BreweryService {
            if (weatherApiService == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(NetworkResponseAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                weatherApiService = retrofit.create(BreweryService::class.java)
            }
            return weatherApiService!!
        }
    }
}