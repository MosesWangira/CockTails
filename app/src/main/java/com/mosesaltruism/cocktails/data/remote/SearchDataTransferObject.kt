package com.mosesaltruism.cocktails.data.remote

//import com.mosesaltruism.cocktails.data.model.search.Drink
//import com.mosesaltruism.cocktails.data.model.search.SearchCockTail
import com.mosesaltruism.cocktails.domain.byname.entities.search.SearchedCockTailItem
import com.squareup.moshi.JsonClass



@JsonClass(generateAdapter = true)
data class NetworkSearchContainer(val drinks: List<Drink>)


@JsonClass(generateAdapter = true)
data class Drink(
//    val dateModified: String,
    val idDrink: String,
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
 * Convert Network results to database objects
 */
fun NetworkSearchContainer.asDatabaseModel(): List<SearchedCockTailItem> {
    return drinks.map {
        SearchedCockTailItem(
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