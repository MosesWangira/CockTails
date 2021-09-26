package com.mosesaltruism.cocktails.data.repository

import com.mosesaltruism.cocktails.core.common.base.BaseRepository
import com.mosesaltruism.cocktails.core.common.helper.DataStorePreference
import com.mosesaltruism.cocktails.core.common.util.DispatcherProvider
import com.mosesaltruism.cocktails.core.common.util.EventStates
import com.mosesaltruism.cocktails.data.local.search.SearchDao
import com.mosesaltruism.cocktails.data.model.search.Drink
import com.mosesaltruism.cocktails.data.remote.NetworkDrink
import com.mosesaltruism.cocktails.data.remote.NetworkSearchContainer
import com.mosesaltruism.cocktails.data.remote.SearchApi
import com.mosesaltruism.cocktails.data.remote.asDatabaseModel
import com.mosesaltruism.cocktails.domain.byname.entities.search.SearchedCockTailItem
import com.mosesaltruism.cocktails.domain.byname.entities.search.asDomainModel
import kotlinx.coroutines.flow.*
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
    suspend fun getSearchedCockTails(name: String = "gin") {
//        val _messageList = MutableStateFlow<EventStates<Response<NetworkSearchContainer>>>(
//            EventStates.Empty
//        )
//        val messages: StateFlow<EventStates<Response<NetworkSearchContainer>>> = _messageList
//
//
//        withContext(dispatchers.io) {
//            _messageList.value = EventStates.Loading
//            _messageList.value = getSearchResponse("Gin")
//
//            messages.collect {
////                if (it is EventStates.Success) {
////                    Timber.d("MosesAltruism: ${it.successResponse.result}")
////                    searchDao.insertAll(it.successResponse.asDatabaseModel())
////                }
//                when (it) {
//                    is EventStates.Success -> {
//                        val m: List<NetworkDrink> = it.successResponse.body()!!.result
//                        Timber.d("MosesAltruism gsr: $messages")
//                        Timber.d("MosesAltruism success: yooh ${it.successResponse.body()?.result}")
//                        //searchDao.insertAll(it.successResponse.body()?.result.asDatabaseModel())
//                    }
//
//                    is EventStates.Loading -> {
//                        Timber.d("MosesAltruism: Loading")
//                    }
//
//                    is EventStates.Empty -> {
//                        Timber.d("MosesAltruism: Empty")
//                    }
//
//                    is EventStates.Failure -> {
//                        Timber.d("MosesAltruism: Failed")
//                    }
//                }
//            }
//        }


//        val m: EventStates<NetworkSearchContainer> = getSearchResponse("gin")

        val emptySearch = MutableStateFlow<EventStates<NetworkSearchContainer>>(
            EventStates.Empty
        )
        val searchList: StateFlow<EventStates<NetworkSearchContainer>> = emptySearch

        //get searched cocktail from datastore
        val searchedName = preferences.searchedCocktailName.first() ?: name

        withContext(dispatchers.io) {
            emptySearch.value = getSearchResponse(searchedName)
            searchList.collect {
//                if (it is EventStates.Success) {
//                    Timber.d("MosesAltruism: ${it.successResponse.result}")
//                    searchDao.insertAll(it.successResponse.asDatabaseModel())
//                }
                when (it) {
                    is EventStates.Success -> {
                        //Timber.d("MosesAltruism gsr: $m")
                        Timber.d("MosesAltruism success: yooh ${it.successResponse.result}")
                        searchDao.insertAll(it.successResponse.asDatabaseModel())
                    }

                    is EventStates.Loading -> {
                        Timber.d("MosesAltruism: Loading")
                    }

                    is EventStates.Empty -> {
                        Timber.d("MosesAltruism: Empty")
                    }

                    is EventStates.Failure -> {
                        Timber.d("MosesAltruism: Failed")
                    }
                }
            }
        }
    }
}