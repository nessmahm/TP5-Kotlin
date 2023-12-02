package com.example.tp5_2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tp5_2.ui.theme.Tp52Theme
import androidx.appcompat.app.AppCompatActivity
import com.example.tp5_2.databinding.ActivityMainBinding
import com.example.tp5_2.weather_modals.WeatherForecastActivity
import com.example.tp5_2.weather_modals.WeatherResponse
import com.google.android.material.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.tp5_2.R.mipmap;


class MainActivity : AppCompatActivity() {
    var weatherViewModel : WeatherViewModel = WeatherViewModel()
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            weatherViewModel.weather.observe(this) {
                if (it != null)
                    setWeather(it)
            }

        val cities = listOf<String>("Tunis", "New York", "Paris", "Rome", "Abidjan", "Russia")
        val citiesAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, cities)
        val spinner = binding.citiesSpinner
        spinner.adapter = citiesAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                weatherViewModel.changeCity(cities[p2])
                if(weatherViewModel.weather.value != null){
                    setWeather(weatherViewModel.weather.value!!)
                }
                println("spinner ${cities[p2]}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                weatherViewModel.changeCity(cities[0])
                if(weatherViewModel.weather.value != null){
                    setWeather(weatherViewModel.weather.value!!)
                }
            }

        }

        binding.forecastButton.setOnClickListener{
            val intent = Intent(this, ForecastActivity::class.java)
            intent.putExtra("city", spinner.selectedItem.toString())
            startActivity(intent)
        }


    }
    fun setWeather(weather : WeatherResponse){
        binding.placeName.text = weather.name
        binding.degree.text = "${weather.main.temp.toString()}°C"
        binding.weather.text = weather.weather[0].description
        binding.humidity.text = "Humidity : ${weather.main.humidity}"
        binding.pressure.text = "Pressure : ${weather.main.pressure}"
        binding.weatherIcon.setImageResource(mipmap.sun)
        if (weather.weather[0].description.contains("rain", ignoreCase = true)) {
            binding.weatherIcon.setImageResource(mipmap.rain)
        }
        if (weather.weather[0].description.contains("snow", ignoreCase = true)) {
            binding.weatherIcon.setImageResource(mipmap.snow)
        }
        if (weather.weather[0].description.contains("clouds", ignoreCase = true)) {
            binding.weatherIcon.setImageResource(mipmap.clouds)
        }
        if (weather.weather[0].description.contains("clear sky", ignoreCase = true)) {
            binding.weatherIcon.setImageResource(mipmap.sun)
        }
        println("Weather info :  ${weather.name}")


    }




}
