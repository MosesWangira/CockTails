package com.mosesaltruism.cocktails.domain.byname.entities.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * SearchedCockTail represents a cocktail entity in the database.
 */
@Entity(tableName = "searched_cocktail")
data class SearchedCockTailItem constructor(
    @PrimaryKey
    val idDrink: String,

    @ColumnInfo(name = "dateModified")
    val dateModified: String,

    @ColumnInfo(name = "strAlcoholic")
    val strAlcoholic: String,

    @ColumnInfo(name = "strCategory")
    val strCategory: String,

    @ColumnInfo(name = "strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: String,

    @ColumnInfo(name = "strDrink")
    val strDrink: String,

    @ColumnInfo(name = "strDrinkThumb")
    val strDrinkThumb: String,

    @ColumnInfo(name = "strGlass")
    val strGlass: String,

    @ColumnInfo(name = "strImageSource")
    val strImageSource: String,

    @ColumnInfo(name = "strInstructions")
    val strInstructions: String,

    @ColumnInfo(name = "strInstructionsDE")
    val strInstructionsDE: String,

    @ColumnInfo(name = "strInstructionsIT")
    val strInstructionsIT: String
)
