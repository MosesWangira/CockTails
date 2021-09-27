package com.mosesaltruism.cocktails.data.repository

import android.util.Log
import android.widget.Toast
import com.mosesaltruism.cocktails.core.common.base.BaseRepository
import com.mosesaltruism.cocktails.core.common.helper.DataStorePreference
import com.mosesaltruism.cocktails.core.common.helper.ginListArray
import com.mosesaltruism.cocktails.core.common.util.DispatcherProvider
import com.mosesaltruism.cocktails.core.common.util.EventStates
import com.mosesaltruism.cocktails.data.local.search.SearchDao
import com.mosesaltruism.cocktails.data.model.search.Drinks
import com.mosesaltruism.cocktails.data.remote.Drink
import com.mosesaltruism.cocktails.data.remote.NetworkSearchContainer
import com.mosesaltruism.cocktails.data.remote.SearchApi
import com.mosesaltruism.cocktails.data.remote.asDatabaseModel
import com.mosesaltruism.cocktails.domain.byname.entities.search.asDomainModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
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

    val cockTailsSearched: Flow<List<Drinks>> =
        searchDao.getCockTailNameList().map {
            it.asDomainModel()
        }

    suspend fun getSearchedCockTails(name: String = "gin") {
//        val messages = MutableStateFlow<EventStates<NetworkSearchContainer>>(
//            EventStates.Empty
//        )

        val moses: Flow<EventStates<NetworkSearchContainer>> = flow {
            val latest = getSearchResponse(name)
            emit(latest)
        }

        moses.collect {
            when (it) {
                is EventStates.Success -> {
                    Timber.d("MosesAltruism messages: moo ${it.successResponse.drinks.size}")
                    val n = it.successResponse.drinks
                    val p = NetworkSearchContainer(n)
                    searchDao.insertAll(p.asDatabaseModel())
                }
            }
        }
    }



//        Timber.d("MosesAltruism: This was called")
//        val _searchedCockTails = MutableStateFlow<EventStates<NetworkSearchContainer>>(
//           EventStates.Loading
//        )
//
//        val searchedCockTails: Flow<EventStates<NetworkSearchContainer>> = _searchedCockTails
//
//
//        _searchedCockTails.value = getSearchResponse("gin")
//        withContext(dispatchers.io){
//            searchedCockTails.collect {
//                when(it){
//                    is EventStates.Success -> {
//                        val flowOfLists: Flow<List<Drink>> = flowOf(it.successResponse.drinks)
//                        val flatList: List<Drink> = flowOfLists.flattenToList()
//
//                        Timber.d("MosesAltruism messages: mooy ${flatList}")
//
//                        Timber.d("MosesAltruism messages: moo ${it.successResponse.drinks}")
//                        val m: List<Drink> = it.successResponse.drinks
//                        val ns = NetworkSearchContainer(m)
//                        searchDao.insertAll(ns.asDatabaseModel())
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
//
//                }
//            }
//        }
//    }
//
//    suspend fun <T> Flow<List<T>>.flattenToList() =
//        flatMapConcat { it.asFlow() }.toList()


    //refresh searched cocktail names
//    suspend fun getSearchedCockTails(name: String = "gin") {
//        val messages = MutableStateFlow<EventStates<NetworkSearchContainer>>(
//            EventStates.Empty
//        )
//
//        val moses: Flow<EventStates<NetworkSearchContainer>> = flow {
//            while (true) {
//                val latest = getSearchResponse(name)
//                emit(latest)
//                delay(5000)
//            }
//        }
//
//        moses.collect {
//            when (it) {
//                is EventStates.Success -> {
//                    Timber.d("MosesAltruism messages: moo ${it.successResponse.drinks}")
//                    //searchDao.insertAll(it.successResponse.body()?.result.asDatabaseModel())
//                }
//            }
//        }
//
//    }

//        withContext(dispatchers.io) {
//            //messages.value = EventStates.Loading
//            messages.value = getSearchResponse("gin")
//
//            messages.collect {
//                when (it) {
//                    is EventStates.Success -> {
//                        Timber.d("MosesAltruism messages: moo ${messages.value}")
//                        Timber.d("MosesAltruism success: yooh ${it.successResponse.body()}")
//                        //searchDao.insertAll(it.successResponse.body()?.result.asDatabaseModel())
//                    }
//                }
//            }
//        }









//        val m: EventStates<NetworkSearchContainer> = getSearchResponse("gin")

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
//            emptySearch.collect {
////                if (it is EventStates.Success) {
////                    Timber.d("MosesAltruism: ${it.successResponse.result}")
////                    searchDao.insertAll(it.successResponse.asDatabaseModel())
////                }
//                when (it) {
//                    is EventStates.Success -> {
//                        //Timber.d("MosesAltruism gsr: $m")
//                        Timber.d("MosesAltruism success: yooh ${it.successResponse.result}")
//                        Timber.d("MosesAltruism success: yooh $searchList")
//                        //searchDao.insertAll(it.successResponse.asDatabaseModel())
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
//    }
}