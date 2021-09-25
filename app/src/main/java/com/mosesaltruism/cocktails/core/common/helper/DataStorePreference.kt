package com.mosesaltruism.cocktails.core.common.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.mosesaltruism.cocktails.core.common.util.Constants.COCKTAIL_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

class DataStorePreference @Inject constructor(@ApplicationContext context: Context) {

    private val appContext = context.applicationContext

    val searchedCocktailName: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[COCKTAIL_NAME]
        }


    suspend fun saveSearchedCockTailName(accessToken: String) {
        appContext.dataStore.edit { preferences ->
            preferences[COCKTAIL_NAME] = accessToken
        }
    }

    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}