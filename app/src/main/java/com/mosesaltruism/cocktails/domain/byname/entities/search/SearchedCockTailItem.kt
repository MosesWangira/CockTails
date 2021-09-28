package com.mosesaltruism.cocktails.domain.byname.entities.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mosesaltruism.cocktails.data.model.search.Drinks

/**
 * SearchedCockTail represents a cocktail entity in the database.
 */
@Entity(tableName = "searched_cocktail")
data class SearchedCockTailItem constructor(
    @PrimaryKey
    val idDrink: String,
//    val dateModified: String,
//    val strAlcoholic: String,
//    val strCategory: String,
//    val strCreativeCommonsConfirmed: String,
    val strDrink: String,
//    val strDrinkThumb: String,
//    val strGlass: String,
//    val strImageSource: String,
//    val strInstructions: String,
//    val strInstructionsDE: String,
//    val strInstructionsIT: String
    )

/**
 * Map SearchedCockTailItem to domain entities
 */
fun List<SearchedCockTailItem>.asDomainModel(): List<Drinks> {
    return map {
        Drinks(
            idDrink = it.idDrink,
//            dateModified = it.dateModified,
//            strAlcoholic = it.strAlcoholic,
//            strCategory = it.strCategory,
//            strCreativeCommonsConfirmed = it.strCreativeCommonsConfirmed,
            strDrink = it.strDrink,
//            strDrinkThumb = it.strDrinkThumb,
//            strGlass = it.strGlass,
//            strImageSource = it.strImageSource,
//            strInstructions = it.strInstructions,
//            strInstructionsDE = it.strInstructionsDE,
//            strInstructionsIT = it.strInstructionsIT
        )
    }
}