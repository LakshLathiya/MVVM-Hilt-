package com.example.lakshlocus.ui.forecastList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lakshlocus.adapter.WeatherRVAdapter
import com.example.lakshlocus.databinding.FragmentForecastBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForecastListFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding
    private val args by navArgs<ForecastListFragmentArgs>()

    @Inject
    lateinit var weatherRVAdapter: WeatherRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        initialize()
        return binding.root
    }

    private fun initialize() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = args.forecast.name
        binding.rvWeather.adapter = weatherRVAdapter
        binding.rvWeather.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        weatherRVAdapter.setWeatherList(args.forecast)
    }
}