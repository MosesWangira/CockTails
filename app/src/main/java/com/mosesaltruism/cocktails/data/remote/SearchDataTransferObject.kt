package com.mosesaltruism.cocktails.data.remote

//import com.mosesaltruism.cocktails.data.model.search.Drink
//import com.mosesaltruism.cocktails.data.model.search.SearchCockTail
import com.mosesaltruism.cocktails.domain.byname.entities.search.SearchedCockTailItem
import com.squareup.moshi.JsonClass



@JsonClass(generateAdapter = true)
data class NetworkSearchContainer(val drinks: List<Drink>)


@JsonClass(generateAdapter = true)
data class Drink(
    val dateModified: String,
    val idDrink: String,
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

////@JsonClass(generateAdapter = true)
//data class NetworkDrink(
//    val idDrink: String,
//    val dateModified: String,
//    val strAlcoholic: String,
//    val strCategory: String,
//    val strCreativeCommonsConfirmed: String,
//    val strDrink: String,
//    val strDrinkThumb: String,
//    val strGlass: String,
//    val strIBA: String,
//    val strImageSource: String,
//    val strInstructions: String,
//    val strInstructionsDE: String,
//    val strInstructionsES: String,
//    val strInstructionsFR: String,
//    val strInstructionsIT: String,
//    val strTags: String
//)

/**
 * Convert Network results to database objects
 */
fun NetworkSearchContainer.asDomainModel(): List<Drink> {
    return drinks.map{
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

/**
 * Convert Network results to database objects
 */
fun NetworkSearchContainer.asDatabaseModel(): List<SearchedCockTailItem> {
    return drinks.map {
        SearchedCockTailItem(
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