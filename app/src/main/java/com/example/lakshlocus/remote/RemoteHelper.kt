package com.example.lakshlocus.remote


import com.example.lakshlocus.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

suspend fun <T> networkCall(request: suspend () -> T): Flow<Resource<T>> {
    return flow {
        try {
            emit(Resource.Loading)
            val response = request.invoke()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}