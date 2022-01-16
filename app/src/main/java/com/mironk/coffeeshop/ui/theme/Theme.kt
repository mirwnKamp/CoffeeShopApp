package com.mironk.coffeeshop.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun CoffeeShopTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {


    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(BackgroundGray, darkIcons = true)

    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}