package com.example.lakshlocus.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(
    val deg: Int,
    val speed: Double
) : Parcelable