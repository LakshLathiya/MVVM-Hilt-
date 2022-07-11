package com.example.lakshlocus.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lakshlocus.databinding.ItemRvWeatherBinding
import com.example.lakshlocus.model.ForeCast
import com.example.lakshlocus.model.Weather
import com.example.lakshlocus.ui.forecastList.ForecastListFragmentDirections
import javax.inject.Inject

class WeatherRVAdapter @Inject constructor() :
    RecyclerView.Adapter<WeatherRVAdapter.MyViewHolder>() {

    var _forecast = mutableListOf<ForeCast>()
    fun setWeatherList(forecast: ForeCast) {
        _forecast.addAll(listOf(forecast))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvWeatherBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val forecast = _forecast[position]
        holder.binding.tvWeather.text = "${forecast.weather.get(0).main}"
        holder.binding.tvTemp.text = "Temp : ${forecast?.main?.temp}"
        holder.binding.root.setOnClickListener {
            _forecast?.let { mainData ->
                holder.binding.root.findNavController()
                    .navigate(
                        ForecastListFragmentDirections.actionForecastToForecastDetails(
                            forecast
                        )
                    )
            }
        }
    }

    override fun getItemCount(): Int {
        return _forecast.size
    }

    class MyViewHolder(val binding: ItemRvWeatherBinding) :
        RecyclerView.ViewHolder(binding.root)
}



