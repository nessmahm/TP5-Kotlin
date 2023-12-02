package com.example.tp5_2
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
public class ForecastViewModel {
    private val forecastResponse : MutableLiveData<ForecastResponse> = MutableLiveData()
    var forecast : LiveData<ForecastResponse> = forecastResponse

    fun getForecast(city : String) {
        val forecastAPI = RetrofitHelper.retrofitService

        val call = forecastAPI.getForecast(city)
        call.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {
                if (response.isSuccessful) {
                    forecastResponse.value = response.body()
                    forecastResponse?.let {
                        println("Weather in  ${city} :  ${it}")
                    }
                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                println("Failed to connect to the weather API")
            }
        })
    }


}
