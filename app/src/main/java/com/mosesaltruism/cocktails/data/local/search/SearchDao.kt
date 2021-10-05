package com.mosesaltruism.cocktails.data.local.search

import androidx.room.*
import com.mosesaltruism.cocktails.domain.byname.entities.search.SearchedCockTailItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    /**
     * Insert list of cocktails in DB
     * get list of cockTails in DB
     * */

    @Query("select * from searched_cocktail")
    fun getCockTailNameList(): Flow<List<SearchedCockTailItem>>

    //for testing
    @Query("select * from searched_cocktail")
    fun getCockTailNameList2(): List<SearchedCockTailItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( searchedCockTail: List<SearchedCockTailItem>)

    @Query("delete from searched_cocktail")
    suspend fun deleteAllSearchedCockTails()

    @Transaction
    suspend fun insertAndDeleteTransaction(searchedCockTail: List<SearchedCockTailItem>){
        //runs in single transaction
        deleteAllSearchedCockTails()
        insertAll(searchedCockTail)
    }
}