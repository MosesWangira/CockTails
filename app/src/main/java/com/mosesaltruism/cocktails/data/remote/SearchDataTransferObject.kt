package com.mosesaltruism.cocktails.data.remote

import com.mosesaltruism.cocktails.domain.byname.entities.search.SearchedCockTailItem
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkSearchContainer(val drinks: List<Drink>)


@JsonClass(generateAdapter = true)
data class Drink(
    val idDrink: String,
    val dateModified: String? = null,
    val strAlcoholic: String? = null,
    val strCategory: String? = null,
    val strCreativeCommonsConfirmed: String? = null,
    val strDrink: String? = null,
    val strDrinkThumb: String? = null,
    val strGlass: String? = null,
    val strImageSource: String? = null,
    val strInstructions: String? = null,
    val strInstructionsDE: String? = null,
    val strInstructionsIT: String? = null
)


/**
 * Convert Network results to database objects
 */
fun NetworkSearchContainer.asDatabaseModel(): List<SearchedCockTailItem> {
    return drinks.map {
        SearchedCockTailItem(
            idDrink = it.idDrink,
            dateModified = it.dateModified ?: "Sorry, No value",
            strAlcoholic = it.strAlcoholic ?: "Sorry, No value",
            strCategory = it.strCategory ?: "Sorry, No value",
            strCreativeCommonsConfirmed = it.strCreativeCommonsConfirmed ?: "Sorry, No value",
            strDrink = it.strDrink ?: "Sorry, No value",
            strDrinkThumb = it.strDrinkThumb ?: "Sorry, No value",
            strGlass = it.strGlass ?: "Sorry, No value",
            strImageSource = it.strImageSource ?: "Sorry, No value",
            strInstructions = it.strInstructions ?: "Sorry, No value",
            strInstructionsDE = it.strInstructionsDE ?: "Sorry, No value",
            strInstructionsIT = it.strInstructionsIT ?: "Sorry, No value"
        )
    }
}