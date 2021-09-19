package com.mosesaltruism.cocktails.core.common.util

import okhttp3.ResponseBody

//Event class for loading, Failure, Empty, Success
sealed class EventStates<out T> {
    object Loading : EventStates<Nothing>()
    object Empty : EventStates<Nothing>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : EventStates<Nothing>()

    data class Success<T>(val successResponse: T) : EventStates<T>()
}
