package com.mosesaltruism.cocktails.domain.byname.entities.search

import androidx.annotation.NonNull
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

    @NonNull
    @ColumnInfo(name = "dateModified")
    val dateModified: String,

    @NonNull
    @ColumnInfo(name = "strAlcoholic")
    val strAlcoholic: String,

    @NonNull
    @ColumnInfo(name = "strCategory")
    val strCategory: String,

    @NonNull
    @ColumnInfo(name = "strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: String,

    @NonNull
    @ColumnInfo(name = "strDrink")
    val strDrink: String,

    @NonNull
    @ColumnInfo(name = "strDrinkThumb")
    val strDrinkThumb: String,

    @NonNull
    @ColumnInfo(name = "strGlass")
    val strGlass: String,

    @NonNull
    @ColumnInfo(name = "strImageSource")
    val strImageSource: String,

    @NonNull
    @ColumnInfo(name = "strInstructions")
    val strInstructions: String,

    @NonNull
    @ColumnInfo(name = "strInstructionsDE")
    val strInstructionsDE: String,

    @NonNull
    @ColumnInfo(name = "strInstructionsIT")
    val strInstructionsIT: String
)
