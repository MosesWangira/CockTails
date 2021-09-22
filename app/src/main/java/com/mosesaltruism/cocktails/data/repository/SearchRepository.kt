package com.mosesaltruism.cocktails.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.google.android.material.internal.FlowLayout
import com.mosesaltruism.cocktails.core.common.base.BaseRepository
import com.mosesaltruism.cocktails.data.remote.SearchApi
import javax.inject.Inject


class SearchRepository @Inject constructor(
    private val api: SearchApi
) : BaseRepository(api) {
    suspend fun getSearchResponse(
        s: String
    ) = safeApiCalls{
        api.getSearchedCocktail(s)
    }
}