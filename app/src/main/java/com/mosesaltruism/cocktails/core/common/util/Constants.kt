package com.mosesaltruism.cocktails.core.common.util

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val BASE_URL = "https://thecocktaildb.com/api/json/v1/1/"
    const val DATABASE_NAME = "cocktail_db"


    //datastore constants
    val COCKTAIL_NAME = stringPreferencesKey("searched_cocktail_name")
    val THEME_STATUS = stringPreferencesKey("theme_status")
}