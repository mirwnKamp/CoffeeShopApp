package com.mironk.coffeeshop.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mironk.coffeeshop.ColdDrinksToolBar
import com.mironk.coffeeshop.SnackTimeToolBar
import com.mironk.coffeeshop.HotDrinksToolBar
import com.mironk.coffeeshop.screens.HomeScreen
import com.mironk.coffeeshop.R
import com.mironk.coffeeshop.screens.CartScreen
import com.mironk.coffeeshop.screens.FavouriteScreen
import com.mironk.coffeeshop.screens.ProfileScreen

sealed class BottomMenuNav(
    val route: String,
    val icon: Int,
    val title: String
) {
    object Home : BottomMenuNav(
        "home",
        R.drawable.home,
        "Home"
    )

    object Favourite : BottomMenuNav(
        "favorite",
        R.drawable.favourite,
        "Favourite"
    )

    object Cart : BottomMenuNav(
        "cart",
        R.drawable.cart,
        "Cart"
    )

    object Profile : BottomMenuNav(
        "profile",
        R.drawable.profile,
        "Profile"
    )
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun BottomNavGraph(navController: NavHostController) {
    val scrollState = rememberLazyListState()
    NavHost(
        navController = navController,
        startDestination = BottomMenuNav.Home.route
    ) {
        composable(route = BottomMenuNav.Home.route){
            HomeScreen(navController = navController)
        }
        composable(route = BottomMenuNav.Favourite.route){
            FavouriteScreen()
        }
        composable(route = BottomMenuNav.Cart.route){
            CartScreen()
        }
        composable(route = BottomMenuNav.Profile.route){
            ProfileScreen()
        }
        composable(
            route = "categories_hot_drinks/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            HotDrinksToolBar(navBackStackEntry.arguments!!.getInt("itemId"), navController = navController)

        }
                composable(
            route = "categories_cold_drinks/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            ColdDrinksToolBar(navBackStackEntry.arguments!!.getInt("itemId"), navController = navController)

        }

        composable(
            route = "categories_snack_time/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            SnackTimeToolBar(navBackStackEntry.arguments!!.getInt("itemId"), navController = navController)


        }

    }
}