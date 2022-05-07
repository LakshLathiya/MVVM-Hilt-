package com.example.lakshlocus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lakshlocus.databinding.ItemRvWeatherBinding
import com.example.lakshlocus.model.Forecast
import com.example.lakshlocus.model.Main
import com.example.lakshlocus.model.Weather
import com.example.lakshlocus.ui.forecastList.ForecastListFragmentDirections
import javax.inject.Inject

class WeatherRVAdapter @Inject constructor() : RecyclerView.Adapter<WeatherRVAdapter.MyViewHolder>() {

    var weather = mutableListOf<Weather>()
    var _forecast: Forecast? = null
    fun setWeatherList(forecast: Forecast) {
        weather.clear()
        weather.addAll(forecast.weather)
        _forecast = forecast
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvWeatherBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val weather = weather[position]
        holder.binding.tvWeather.text = weather.main
        holder.binding.tvTemp.text = "Temp : ${_forecast?.main?.temp}"
        holder.binding.root.setOnClickListener {
            _forecast?.let { mainData ->
                holder.binding.root.findNavController()
                    .navigate(
                        ForecastListFragmentDirections.actionForecastToForecastDetails(weather, mainData)
                    )
            }
        }
    }

    override fun getItemCount(): Int {
        return weather.size
    }

    class MyViewHolder(val binding: ItemRvWeatherBinding) :
        RecyclerView.ViewHolder(binding.root)
}



