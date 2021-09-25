package com.mosesaltruism.cocktails.core.common.helper

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mosesaltruism.cocktails.core.common.base.DataStorePreference
import com.mosesaltruism.cocktails.data.repository.SearchRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import retrofit2.HttpException


@HiltWorker
class WorkerManager @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: SearchRepository,
    private val preferences: DataStorePreference
) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to do your work.
     */
    override suspend fun doWork(): Result {
        return try {
            val searchedName = preferences.searchedCocktailName.first() ?: "Gin"
            repository.getSearchedCockTails(searchedName)
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}