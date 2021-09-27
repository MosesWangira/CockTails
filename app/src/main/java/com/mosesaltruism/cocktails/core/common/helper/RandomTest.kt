package com.mosesaltruism.cocktails.core.common.helper

import com.mosesaltruism.cocktails.data.remote.Drink
import com.mosesaltruism.cocktails.data.remote.NetworkSearchContainer

/**
 * List of gin cocktails
 **/

fun ginListArray(): NetworkSearchContainer {
    val ginList = arrayListOf<Drink>()

    ginList.add(
        Drink(
            "25 july 2019",
            "5678",
            "non-alcoholic",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )
    )

    ginList.add(
        Drink(
            "26 july 2020", "4321", "alcoholic", "", "", "", "", "", "", "", "", "", "", "", "", ""
        )
    )

    ginList.add(
        Drink(
            "27 july 2021",
            "8765",
            "non-alcoholic",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )
    )

    return NetworkSearchContainer(ginList)
}