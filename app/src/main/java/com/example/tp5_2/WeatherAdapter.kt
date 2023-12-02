package com.example.tp5_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter(private val forecast : ForecastResponse?) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val pressure : TextView
        val temperature : TextView
        val humidity : TextView
        val weatherIcon:ImageView
        init {
            pressure = itemView.findViewById(R.id.pressure)
            temperature = itemView.findViewById(R.id.temperature)
            humidity = itemView.findViewById(R.id.humidity)
            weatherIcon = itemView.findViewById(R.id.weatherIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pressure.text = "Pressure ${forecast!!.list[position].pressure.toString()}"
        holder.temperature.text = "Temperature ${forecast!!.list[position].temp.day.toString()}"
        holder.humidity.text = "Humidity ${forecast!!.list[position].humidity.toString()}"
        if (forecast!!.list[position].weather[0].description.contains("rain", ignoreCase = true)) {
            holder.weatherIcon.setImageResource(R.mipmap.rain)
        }
        if (forecast!!.list[position].weather[0].description.contains("snow", ignoreCase = true)) {
            holder.weatherIcon.setImageResource(R.mipmap.snow)
        }
        if (forecast!!.list[position].weather[0].description.contains("clouds", ignoreCase = true)) {
            holder.weatherIcon.setImageResource(R.mipmap.clouds)
        }
        if (forecast!!.list[position].weather[0].description.contains("clear sky", ignoreCase = true)) {
            holder.weatherIcon.setImageResource(R.mipmap.sun)
        }
    }

    override fun getItemCount(): Int {
        if(forecast != null) return forecast.list.size
        return 0
    }
}