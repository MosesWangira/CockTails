package com.mosesaltruism.cocktails.domain.byname.interfaces

import com.mosesaltruism.cocktails.core.common.base.BaseApi
import com.mosesaltruism.cocktails.data.model.home.SearchCockTail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi: BaseApi {
    @GET("/search.php")
    suspend fun getSearchedCocktail(@Query("s") s: String): Response<SearchCockTail>
}