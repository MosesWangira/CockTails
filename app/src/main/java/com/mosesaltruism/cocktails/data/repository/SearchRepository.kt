package com.mosesaltruism.cocktails.data.repository

import com.mosesaltruism.cocktails.core.common.base.BaseRepository
import com.mosesaltruism.cocktails.domain.byname.interfaces.SearchApi
import javax.inject.Inject


class SearchRepository @Inject constructor(
    private val api: SearchApi
) : BaseRepository(api) {
    suspend fun getSearchResponse() = safeApiCalls(api::getSearchedCocktail)
}