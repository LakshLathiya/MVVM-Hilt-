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
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = args.mainData.name
        binding.tvTemp.text = args.mainData.main.temp.toString()
        binding.tvFeelLike.text = "Feels like: ${args.mainData.main.feels_like}"
        binding.tvWeather.text = args.weather.main
        binding.tvWeatherDes.text = args.weather.description
    }

}