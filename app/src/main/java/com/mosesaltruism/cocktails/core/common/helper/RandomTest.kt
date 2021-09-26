package com.mosesaltruism.cocktails.core.common.helper

import com.mosesaltruism.cocktails.data.remote.NetworkDrink
import com.mosesaltruism.cocktails.data.remote.NetworkSearchContainer

/**
 * List of gin cocktails
 **/

fun ginListArray(): NetworkSearchContainer {
    val ginList = arrayListOf<NetworkDrink>()
    ginList.add(
        NetworkDrink(
            "24 july 2018", "1234", "alcoholic", "", "", "", "", "", "", "", "", "", "", "", "", ""
        )
    )

    ginList.add(
        NetworkDrink(
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
        NetworkDrink(
            "26 july 2020", "4321", "alcoholic", "", "", "", "", "", "", "", "", "", "", "", "", ""
        )
    )

    ginList.add(
        NetworkDrink(
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