package com.example.tp5_2
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tp5_2.weather_modals.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
public class WeatherViewModel {
        private val weatherResponse : MutableLiveData<WeatherResponse> = MutableLiveData()
        var weather : LiveData<WeatherResponse> = weatherResponse

         private fun getWeatherData(city : String) {
            val weatherAPI = RetrofitHelper.retrofitService
             println("api ${weatherAPI}")
             println("city ${city}")

            val call = weatherAPI.getWeather(city)
            call.enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        weatherResponse.value = response.body()
                        weatherResponse?.let {
                            println("weather respons in  ${city} :  ${weatherResponse.value}")
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    println("Failed to connect to the weather API $t")
                }
            })
        }
         fun changeCity(city : String) : String?{
             getWeatherData(city)
            weather = weatherResponse
            return weatherResponse.value?.name
         }

    }
