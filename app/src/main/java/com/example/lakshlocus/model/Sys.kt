package com.example.lakshlocus.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
): Parcelable