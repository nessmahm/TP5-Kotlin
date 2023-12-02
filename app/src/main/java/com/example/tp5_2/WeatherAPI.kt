package com.example.tp5_2

import com.example.tp5_2.weather_modals.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather?q=Tunis&APPID=17db59488cadcad345211c36304a9266&units=metric")
    fun getTunisWeather() : Call<WeatherResponse>

    @GET("weather?APPID=731e961517486404c2ea774dede03efb&units=metric")
    fun getWeather(@Query("q") city : String) : Call<WeatherResponse>

    @GET("forecast/daily?APPID=17db59488cadcad345211c36304a9266&units=metric")
    fun getForecast(@Query("q") city : String) : Call<ForecastResponse>
}
