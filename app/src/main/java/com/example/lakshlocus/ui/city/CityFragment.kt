package com.example.lakshlocus.ui.city

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lakshlocus.databinding.FragmentCityBinding
import com.example.lakshlocus.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CityFragment : Fragment() {

    private lateinit var binding: FragmentCityBinding
    private val cityViewModel by viewModels<CityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cityViewModel.uiState.observe(this) {
            onObserveUiState(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        binding.tvLookup.setOnClickListener {
            if (Utils.isNetworkAvailable(requireContext())) {
                cityViewModel.fetchForecastDetails(binding.etCity.text.toString())
                Utils.hideSoftKeyBoard(requireContext(), binding.etCity)
            } else {
                Utils.showToast("Please check your internet Connection", requireContext())
            }
        }
    }

    private fun onObserveUiState(uiState: CityViewModel.UiState) {
        when (uiState) {
            is CityViewModel.UiState.DataState -> {
                binding.progress.visibility = View.GONE
                if (uiState.forecast.isSuccessful) {
                    Log.e("TAG", uiState.forecast.body()!!.toString())
                    findNavController().navigate(
                        CityFragmentDirections.actionCityScreenToForecast(uiState.forecast.body()!!)
                    )
                    binding.etCity.setText("")
                }else{
                    binding.progress.visibility = View.GONE
                    binding.tvError.setText(uiState.forecast.message())
                    binding.etCity.setText("")
                    Log.e("TAG", uiState.forecast.message())
                }
            }
            is CityViewModel.UiState.Error -> {
                binding.progress.visibility = View.GONE
                binding.tvError.setText(uiState.error.message)
                binding.etCity.setText("")
            }
            is CityViewModel.UiState.InvalidCityState -> {
                binding.etCity.error = "Please enter valid city"
            }
            is CityViewModel.UiState.LoadingState -> {
                binding.progress.visibility = View.VISIBLE
                binding.etCity.error = null
            }
        }
    }
}