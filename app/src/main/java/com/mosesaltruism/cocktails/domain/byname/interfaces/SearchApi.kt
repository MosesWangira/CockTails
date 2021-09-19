package com.mosesaltruism.cocktails.domain.byname.interfaces

import com.mosesaltruism.cocktails.core.common.base.BaseApi
import com.mosesaltruism.cocktails.data.model.home.SearchCockTail
import retrofit2.Response
import retrofit2.http.GET

interface SearchApi: BaseApi {
    @GET("/search.php?s=gin")
    suspend fun getSearchedCocktail(): Response<SearchCockTail>
}