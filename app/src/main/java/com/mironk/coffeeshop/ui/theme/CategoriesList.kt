package com.mironk.coffeeshop.ui.theme

import androidx.annotation.DrawableRes
import com.mironk.coffeeshop.R

data class CategoriesList(
    @DrawableRes val iconId: Int,
    val coffee: String
)

val categoriesList = arrayListOf<CategoriesList>(
    CategoriesList(
        R.drawable.hot_drinks,
        "Hot Drinks"
    ),
    CategoriesList(
        R.drawable.cold_drinks,
        "Cold Drinks"
    ),
    CategoriesList(
        R.drawable.snack_time,
        "Snack Time"
    )
)