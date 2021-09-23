package com.mosesaltruism.cocktails.data.repository

import com.mosesaltruism.cocktails.core.common.base.BaseRepository
import com.mosesaltruism.cocktails.core.common.base.DataStorePreference
import com.mosesaltruism.cocktails.core.common.util.DispatcherProvider
import com.mosesaltruism.cocktails.core.common.util.EventStates
import com.mosesaltruism.cocktails.data.local.search.SearchDao
import com.mosesaltruism.cocktails.data.model.search.SearchCockTail
import com.mosesaltruism.cocktails.data.remote.NetworkDrink
import com.mosesaltruism.cocktails.data.remote.NetworkSearchContainer
import com.mosesaltruism.cocktails.data.remote.SearchApi
import com.mosesaltruism.cocktails.data.remote.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


class SearchRepository @Inject constructor(
    private val api: SearchApi,
    private val preferences: DataStorePreference,
    private val dispatchers: DispatcherProvider,
    private val searchDao: SearchDao
) : BaseRepository(api) {
    suspend fun getSearchResponse(
        s: String
    ) = safeApiCalls{
        api.getSearchedCocktail(s)
    }

    //refresh searched cocktail names
    suspend fun refreshSearchedCockTails() {
        val emptySearch = MutableStateFlow<EventStates<NetworkSearchContainer>>(
            EventStates.Empty
        )
        val searchList: StateFlow<EventStates<NetworkSearchContainer>> = emptySearch

        //get searched cocktail from database
        val searchedName = preferences.searchedCocktailName.first()

        withContext(dispatchers.io) {
            emptySearch.value = getSearchResponse(searchedName ?: "Gin")
            searchList.collect {
                if (it is EventStates.Success) {
                    searchDao.insertAll(it.successResponse.asDatabaseModel())
                }
            }
        }
    }
}