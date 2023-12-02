package com.example.tp5_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp5_2.databinding.ForecastActivityBinding


class ForecastActivity : AppCompatActivity() {
    private lateinit var binding : ForecastActivityBinding
    var forecastViewModel : ForecastViewModel = ForecastViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ForecastActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val city=intent.getStringExtra("city")

        if(city != null){
            forecastViewModel.getForecast(city)
        }

        forecastViewModel.forecast.observe(this) {
            if (it != null){
                binding.forecastsRecycler.adapter = WeatherAdapter(forecastViewModel.forecast.value)
                binding.cityName.text = forecastViewModel.forecast.value!!.city.name

            }
        }

        binding.forecastsRecycler.apply {
            layoutManager = LinearLayoutManager(this@ForecastActivity)
            adapter = WeatherAdapter(forecastViewModel.forecast.value)
        }


    }



}