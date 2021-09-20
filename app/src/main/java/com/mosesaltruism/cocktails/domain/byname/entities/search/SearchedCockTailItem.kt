package com.mosesaltruism.cocktails.domain.byname.entities.search

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * SearchedCockTail represents a cocktail entity in the database.
 */
@Entity(tableName = "searched_cocktail")
data class SearchedCockTailItem constructor(
    @PrimaryKey
    val title: String,

)