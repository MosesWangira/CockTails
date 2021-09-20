package com.mosesaltruism.cocktails.data.local.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( searchedCockTail: List<SearchedCockTailItem>)
}