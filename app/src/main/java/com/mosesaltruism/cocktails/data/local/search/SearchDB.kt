package com.mosesaltruism.cocktails.data.local.search

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mosesaltruism.cocktails.domain.byname.entities.search.SearchedCockTailItem


@Database(
    entities = [SearchedCockTailItem::class],
    version = 1
)
abstract class SearchDB: RoomDatabase() {

    abstract fun searchDao(): SearchDao
}