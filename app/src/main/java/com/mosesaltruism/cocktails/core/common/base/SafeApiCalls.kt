package com.mosesaltruism.cocktails.core.common.base

import com.mosesaltruism.cocktails.core.common.util.EventStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCalls {
    suspend fun <T> safeApiCalls(
        apiCalls: suspend () -> T
    ): EventStates<T> {
        return withContext(Dispatchers.IO) {
            try {
                EventStates.Success(apiCalls.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        EventStates.Failure(
                            false,
                            throwable.code(),
                            throwable.response()?.errorBody()
                        )
                    }
                    else -> {
                        EventStates.Failure(true, null, null)
                    }
                }
            }
        }
    }
}
