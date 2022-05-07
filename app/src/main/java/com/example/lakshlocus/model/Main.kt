package com.example.lakshlocus.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
) : Parcelable