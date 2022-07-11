package com.example.lakshlocus.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForeCast (
    val main: Main,
    val weather: List<Weather>,
): Parcelable