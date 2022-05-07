package com.example.lakshlocus.model

sealed class Resource<out T> {

    object Loading : Resource<Nothing>()

    data class Success<T>(val data: T): Resource<T>()

    data class Error(val error: Throwable) : Resource<Nothing>()
}