package com.mosesaltruism.cocktails.data.repository

import com.mosesaltruism.cocktails.core.common.base.BaseRepository
import com.mosesaltruism.cocktails.core.common.base.ginListArray
import com.mosesaltruism.cocktails.core.common.helper.DataStorePreference
import com.mosesaltruism.cocktails.core.common.util.DispatcherProvider
import com.mosesaltruism.cocktails.data.local.search.SearchDao
import com.mosesaltruism.cocktails.data.model.search.Drink
import com.mosesaltruism.cocktails.data.remote.SearchApi
import com.mosesaltruism.cocktails.data.remote.asDatabaseModel
import com.mosesaltruism.cocktails.domain.byname.entities.search.asDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SearchRepository @Inject constructor(
    private val api: SearchApi,
    private val preferences: DataStorePreference,
    private val dispatchers: DispatcherProvider,
    private val searchDao: SearchDao
) : BaseRepository(api) {
    private suspend fun getSearchResponse(
        s: String
    ) = safeApiCalls{
        api.getSearchedCocktail(s)
    }

    val cockTailsSearched: Flow<List<Drink>> =
        searchDao.getCockTailNameList().map {
            it.asDomainModel()
        }

    //refresh searched cocktail names
    suspend fun getSearchedCockTails(name: String = "Gin"){
        //test if array is inserted into database
        withContext(dispatchers.io){
            searchDao.insertAll(ginListArray().asDatabaseModel())
        }

//        val emptySearch = MutableStateFlow<EventStates<NetworkSearchContainer>>(
//            EventStates.Empty
//        )
//        val searchList: StateFlow<EventStates<NetworkSearchContainer>> = emptySearch
//
//        //get searched cocktail from datastore
//        val searchedName = preferences.searchedCocktailName.first() ?: name
//
//        withContext(dispatchers.io) {
//            emptySearch.value = getSearchResponse(searchedName)
//            searchList.collect {
//                if (it is EventStates.Success) {
//                    val n: NetworkSearchContainer = it.successResponse
//                    searchDao.insertAll(it.successResponse.asDatabaseModel())
//                }
//            }
//        }
    }
}