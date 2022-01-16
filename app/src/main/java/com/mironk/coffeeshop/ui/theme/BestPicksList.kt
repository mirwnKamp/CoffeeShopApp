package com.mironk.coffeeshop.ui.theme

import androidx.annotation.DrawableRes
import com.mironk.coffeeshop.R

data class BestPicksList(
    @DrawableRes val image: Int,
    val name: String,
    val size: String,
    val price: String
)

val bestPicks = arrayListOf<BestPicksList>(
    BestPicksList(
        R.drawable.freddo_espresso,
    "Freddo Espresso",
    "served in 12oz cup",
    "2.00 €"),
    BestPicksList(
        R.drawable.cappuccino,
    "Cappuccino",
    "served in 8oz cup",
    "2.00 €"),
    BestPicksList(
        R.drawable.chocolate_bar,
    "Protein Bar",
    "with Chocolate Chip",
    "2.30 €")

)