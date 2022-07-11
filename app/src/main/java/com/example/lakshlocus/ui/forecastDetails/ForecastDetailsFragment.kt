package com.example.lakshlocus.ui.forecastDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.lakshlocus.databinding.FragmentForecastDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastDetailsFragment : Fragment() {

    private lateinit var binding: FragmentForecastDetailsBinding
    private val args by navArgs<ForecastDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastDetailsBinding.inflate(inflater, container, false)
        initialize()
        return binding.root
    }

    private fun initialize() {
        binding.tvTemp.text = args.forecast.main.temp.toString()
        binding.tvFeelLike.text = "Feels like: ${args.forecast.main.feels_like}"
        binding.tvWeather.text = args.forecast.weather.get(0).main
        binding.tvWeatherDes.text = args.forecast.weather.get(0).description
    }

}