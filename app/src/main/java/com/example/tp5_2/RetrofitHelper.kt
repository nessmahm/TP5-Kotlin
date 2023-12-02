package com.example.tp5_2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val baseUrl ="https://api.openweathermap.org/data/2.5/"

    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        // we need to add converter factory to
        // convert JSON object to Java object
        .build()

    val retrofitService : WeatherAPI by lazy { retrofit.create(WeatherAPI::class.java) }

}
