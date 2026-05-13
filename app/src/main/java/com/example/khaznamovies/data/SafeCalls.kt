package com.example.khaznamovies.data

import com.example.khaznamovies.domain.models.Status

suspend fun <T> safeApiCalls(
    isConnected: Boolean,
    apiCall: suspend () -> T
) = if (isConnected) {
    runCatching {
        Status.Success(apiCall.invoke())
    }.getOrElse { throwable ->
        Status.Error(error = throwable.message)
    }
} else {
    Status.NoNetwork(error = "No Network")
}