package com.example.lakshlocus.repository

import com.example.lakshlocus.model.Forecast
import com.example.lakshlocus.model.Resource
import com.example.lakshlocus.remote.ForecastService
import com.example.lakshlocus.remote.networkCall
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


class ForecastRepository @Inject constructor(private val forecastService: ForecastService) {
    suspend fun getForecast(city: String): Flow<Resource<Response<Forecast>>> =
        networkCall {
            forecastService.getForecast(city)
        }
}