package com.example.lakshlocus.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lakshlocus.model.Forecast
import com.example.lakshlocus.model.Resource
import com.example.lakshlocus.repository.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val repository: ForecastRepository) : ViewModel() {

    private val uiStateData = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = uiStateData
    fun fetchForecastDetails(city: String) {
        if (city.isBlank()) {
            uiStateData.postValue(UiState.InvalidCityState)
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getForecast(city).collect {
                    uiStateData.postValue(
                        when (it) {
                            is Resource.Loading -> UiState.LoadingState
                            is Resource.Error -> UiState.Error(it.error)
                            is Resource.Success -> UiState.DataState(it.data)
                        }
                    )
                }
            }
        }
    }

    sealed class UiState {

        object InvalidCityState : UiState()

        object LoadingState : UiState()

        data class Error(val error: Throwable) : UiState()

        data class DataState(val forecast: Response<Forecast>) : UiState()

    }

}