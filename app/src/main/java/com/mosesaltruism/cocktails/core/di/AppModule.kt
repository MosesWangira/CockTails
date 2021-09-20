package com.mosesaltruism.cocktails.core.di

import android.content.Context
import androidx.room.Room
import com.mosesaltruism.cocktails.core.common.base.SafeApiCalls
import com.mosesaltruism.cocktails.core.common.util.Constants.BASE_URL
import com.mosesaltruism.cocktails.core.common.util.Constants.DATABASE_NAME
import com.mosesaltruism.cocktails.core.common.util.DispatcherProvider
import com.mosesaltruism.cocktails.data.local.search.SearchDB
import com.mosesaltruism.cocktails.data.repository.SearchRepository
import com.mosesaltruism.cocktails.data.remote.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCocktailsApi(): SearchApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SearchApi::class.java)

    @Singleton
    @Provides
    fun provideSearchedCockTail(api: SearchApi): SafeApiCalls = SearchRepository(api)


    /**
     * Room db
     */
    @Singleton
    @Provides
    fun provideSearchDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, SearchDB::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideSearchDao(
        database: SearchDB
    ) = database.searchDao()



    /**
     * Dispatchers
     * */
    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}