package com.example.lakshlocus.remote


import com.example.lakshlocus.model.ForecastModel
import com.example.lakshlocus.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("APPID") appId: String = Constants.APP_ID
    ): Response<ForecastModel>
}