package com.mosesaltruism.cocktails.domain.byname.entities.search

import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val strInstructionsES: Any,
    val strInstructionsFR: Any,
    val strInstructionsIT: String,
    val strTags: String
)