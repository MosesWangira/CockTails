package com.mosesaltruism.cocktails.data.local.search

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.mosesaltruism.cocktails.domain.byname.entities.search.SearchedCockTailItem
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SearchDBTest : TestCase() {
    private lateinit var searchDao: SearchDao
    private lateinit var db: SearchDB


    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, SearchDB::class.java
        ).build()
        searchDao = db.searchDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertedCocktailInGetCockTails() = runBlocking {
        val list = arrayListOf<SearchedCockTailItem>()
        val cockTail = SearchedCockTailItem(
            "1234", "12th july, 2021", "alcoholic", "m",
            "m", "m", "m", "m", "m",
            "m", "m", "m"
        )
        list.add(cockTail)

        searchDao.insertAll(list)
        val sizeCOckTail = searchDao.getCockTailNameList2().size

        assertThat(sizeCOckTail == 1).isTrue()
    }

    @Test
    fun deleteCockTail() = runBlocking {
        val list = arrayListOf<SearchedCockTailItem>()
        val cockTail = SearchedCockTailItem(
            "1234", "12th july, 2021", "alcoholic", "m",
            "m", "m", "m", "m", "m",
            "m", "m", "m"
        )
        list.add(cockTail)

        searchDao.insertAll(list)
        searchDao.deleteAllSearchedCockTails()
        val sizeCOckTail = searchDao.getCockTailNameList2().size

        assertThat(sizeCOckTail == 0).isTrue()
    }
}