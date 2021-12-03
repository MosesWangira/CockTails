package com.mosesaltruism.cocktails.data.remote

import com.mosesaltruism.cocktails.core.common.base.BaseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi : BaseApi {
    @GET("search.php")
    suspend fun getSearchedCocktail(@Query("s") s: String): NetworkSearchContainer
}