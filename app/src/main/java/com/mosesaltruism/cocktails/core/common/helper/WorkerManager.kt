package com.mosesaltruism.cocktails.core.common.helper

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mosesaltruism.cocktails.data.repository.SearchRepository
import com.mosesaltruism.cocktails.presentation.byname.views.Home
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException


/***
 * This class will never be used probably
 * */

@HiltWorker
class WorkerManager @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: SearchRepository,
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
            //repository.getSearchResponse("margarita")
            //Home().getRemoteSearchedCockTail()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}