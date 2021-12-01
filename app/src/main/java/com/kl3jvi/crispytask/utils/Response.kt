package com.kl3jvi.crispytask.utils

sealed class Response<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Response<T>(data)
    class Error<T>(message: String?) : Response<T>(message = message)
    class Loading<T>(data: T? = null) : Response<T>(data)
}


