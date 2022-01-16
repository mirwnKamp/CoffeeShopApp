package com.mironk.coffeeshop.ui.theme

data class ContentSize(val title: String, val size: String)


val hotCoffees = arrayListOf<ContentSize>(
    ContentSize("Coffee Size", "Small"),
    ContentSize("Coffee Size", "Medium"),
    ContentSize("Coffee Size", "Large")
)
val coldCoffees = arrayListOf<ContentSize>(
    ContentSize("Coffee Size", "Medium"),
    ContentSize("Coffee Size", "Large")
)
val smoothies = arrayListOf<ContentSize>(
    ContentSize("Smoothie Size", "Large")
)

