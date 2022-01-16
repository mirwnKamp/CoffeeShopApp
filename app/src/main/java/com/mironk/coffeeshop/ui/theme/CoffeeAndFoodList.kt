package com.mironk.coffeeshop.ui.theme

import com.mironk.coffeeshop.R

data class CoffeeAndFoodList constructor(
    val id: Int,
    val contentPhoto: Int,
    val contentSize: String,
    val contentName: String,
    val price: String,
    val about: String
)


val HotCoffeeList = arrayListOf<CoffeeAndFoodList>(
    CoffeeAndFoodList(
        0,
        R.drawable.espresso,
        "served in 8oz Cup",
        "Espresso",
        "1.50 €",
        "Espresso is generally thicker than coffee brewed by other methods, with a viscosity of warm honey. This is due to the higher concentration of suspended and dissolved solids, and the crema on top. "
    ),
    CoffeeAndFoodList(
        1,
        R.drawable.cappuccino,
        "served in 8oz Cup",
        "Cappuccino",
        "2.00 €",
        "A cappuccino is a coffee-based drink made primarily from espresso and milk. It consists of one-third espresso, one-third heated milk and one-third milk foam and is generally served in a 6 to 8-ounce cup. The cappuccino is considered one of the original espresso drinks representative of Italian espresso cuisine and eventually Italian-American espresso cuisine."
    ),
    CoffeeAndFoodList(
        2,
        R.drawable.hot_chocolate,
        "served in 12oz Cup",
        "Chocolate",
        "2.50 €",
        "Hot Chocolate is a warm, chocolate drink, as implied by the name. It is a common beverage in the U.S. It is useful to drink it during the winter season. \nThe reason is because it gives your body heat. You can also drink it during other seasons, but it is mostly usually popular in the winter."
    )
)

val ColdCoffeeList = arrayListOf<CoffeeAndFoodList>(
    CoffeeAndFoodList(
        0,
        R.drawable.freddo_espresso,
        "served in 12oz Cup",
        "Freddo \nEspresso",
        "2.00 €",
        "Due to the boiling summer condition in Greece, and the need to escape and alleviate the heat, individuals began to invent and eventually produce cold variations of the famous coffee. The drink consists of espresso shots, around 30 to 40 milliliters, sugar, and ice. The Espresso is then mixed with the sugar in a drink mixer, it then combines the coffee with its sugar, producing foam from the oils of Espresso, and eventually lowers its temperature. \nThe coffee then is poured over ice into the serving glass. The melting of the ice cubes dilutes the coffee mix and results in a desirable level of bitterness and creamy taste."
    ),
    CoffeeAndFoodList(
        1,
        R.drawable.freedo_cappuccino,
        "served in 12oz Cup",
        "Freddo \nCappuccino",
        "2.20 €",
        "The freddo is a Greek beverage that’s been sipped for decades. It’s an iced coffee drink made from fresh espresso and frothed milk, and it combines the refreshment of a cold coffee with the robust flavor of a double espresso. It’s no surprise that this drink is served cold by default, since Greece is pretty warm and sunny year-round."
    ),
    CoffeeAndFoodList(
        2,
        R.drawable.milkshake_choco,
        "served in 16oz Cup\n",
        "MilkShake",
        "3.50 €",
        "Milkshakes originated in the United States around the turn of the 20th century, and grew in popularity following the introduction of electric blenders in the subsequent two decades. They became a common part of youth popular culture, as ice cream shops were a culturally acceptable meeting place for youth, and milkshakes became symbolic of the innocence of youth."
    )
)

val SnackTimeList = arrayListOf<CoffeeAndFoodList>(
    CoffeeAndFoodList(
        0,
        R.drawable.chocolate_bar,
        "with Chocolate Chip",
        "Protein Bar",
        "2.30 €",
        ""
    ),
    CoffeeAndFoodList(
        1,
        R.drawable.blueberry_muffin,
        "Paleo Blueberry",
        "Muffin",
        "2.50 €",
        ""
    ),
    CoffeeAndFoodList(
        2,
        R.drawable.chicken_tortilla,
        "Chicken with salad",
        "Tortilla Wrap",
        "3.80 €",
        ""
    )
)