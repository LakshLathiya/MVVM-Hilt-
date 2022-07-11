package com.example.lakshlocus.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForeCast>,
    val message: Int
): Parcelable