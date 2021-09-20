package com.mosesaltruism.cocktails.domain.byname.entities.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mosesaltruism.cocktails.data.model.search.Drink

/**
 * SearchedCockTail represents a cocktail entity in the database.
 */
@Entity(tableName = "searched_cocktail")
data class SearchedCockTailItem constructor(
    @PrimaryKey
    val idDrink: String,
    val dateModified: String,
    val strAlcoholic: String,
    val strCategory: String,
    val strCreativeCommonsConfirmed: String,
    val strDrink: String,
    val strDrinkThumb: String,
    val strGlass: String,
    val strIBA: String,
    val strImageSource: String,
    val strInstructions: String,
    val strInstructionsDE: String,
    val strInstructionsES: String,
    val strInstructionsFR: String,
    val strInstructionsIT: String,
    val strTags: String
)

/**
 * Map SearchedCockTailItem to domain entities
 */
fun List<SearchedCockTailItem>.asDomainModel(): List<Drink> {
    return map {
        Drink(
            idDrink = it.idDrink,
            dateModified = it.dateModified,
            strAlcoholic = it.strAlcoholic,
            strCategory = it.strCategory,
            strCreativeCommonsConfirmed = it.strCreativeCommonsConfirmed,
            strDrink = it.strDrink,
            strDrinkThumb = it.strDrinkThumb,
            strGlass = it.strGlass,
            strIBA = it.strIBA,
            strImageSource = it.strImageSource,
            strInstructions = it.strInstructions,
            strInstructionsDE = it.strInstructionsDE,
            strInstructionsES = it.strInstructionsES,
            strInstructionsFR = it.strInstructionsFR,
            strInstructionsIT = it.strInstructionsIT,
            strTags = it.strTags
        )
    }
}