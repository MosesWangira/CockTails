package com.mosesaltruism.cocktails.data.repository

import com.mosesaltruism.cocktails.core.common.base.BaseRepository
import com.mosesaltruism.cocktails.core.common.util.DispatcherProvider
import com.mosesaltruism.cocktails.data.local.search.SearchDao
import com.mosesaltruism.cocktails.data.remote.SearchApi
import com.mosesaltruism.cocktails.domain.byname.entities.search.SearchedCockTailItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SearchRepository @Inject constructor(
    private val api: SearchApi,
    private val dispatchers: DispatcherProvider,
    private val searchDao: SearchDao
) : BaseRepository(api) {

    //get response from internet
    suspend fun getSearchResponse(
        s: String
    ) = safeApiCalls{
        api.getSearchedCocktail(s)
    }

    //get searched cocktails from database
    val getCockTailsSearched: Flow<List<SearchedCockTailItem>> = searchDao.getCockTailNameList()

//    //insert searched cocktails to database
//    suspend fun insertCockTailToDB(searchedCockTail: List<SearchedCockTailItem>){
//        withContext(dispatchers.io){
//            searchDao.insertAll(searchedCockTail)
//        }
//    }

    suspend fun deleteAndInsertNewCockTails(searchedCockTail: List<SearchedCockTailItem>){
        withContext(dispatchers.io){
            searchDao.insertAndDeleteTransaction(searchedCockTail)
        }
    }

//    //delete all searched cocktails
//    suspend fun deleteAllSearchedCockTails(){
//        withContext(dispatchers.io){
//            searchDao.deleteAllSearchedCockTails()
//        }
//    }
}