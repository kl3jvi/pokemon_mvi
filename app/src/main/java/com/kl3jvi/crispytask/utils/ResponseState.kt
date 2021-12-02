package com.kl3jvi.crispytask.utils

sealed class ResponseState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : ResponseState<T>(data)
    class Error<T>(message: String?) : ResponseState<T>(message = message)
    class Loading<T>(data: T? = null) : ResponseState<T>(data)
}


