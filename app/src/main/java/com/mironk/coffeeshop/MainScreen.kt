package com.mironk.coffeeshop

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mironk.coffeeshop.screens.HomeScreen
import com.mironk.coffeeshop.ui.theme.*

@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MainScreen() {

    val navController = rememberNavController()


    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }

}

@ExperimentalAnimationApi
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val screens = listOf(
        BottomMenuNav.Home,
        BottomMenuNav.Favourite,
        BottomMenuNav.Cart,
        BottomMenuNav.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier.graphicsLayer {
            shape = RoundedCornerShape(
                topStart = 20.dp, topEnd = 20.dp
            )
            clip = true
        }
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
                isSelected = true
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun RowScope.AddItem(
    screen: BottomMenuNav,
    currentDestination: NavDestination?,
    navController: NavHostController,
    isSelected: Boolean
) {
    BottomNavigationItem(
        label = {
            AnimatedVisibility(visible = isSelected) {
                Text(text = screen.title)
            }

        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon",
                modifier = Modifier
                    .size(20.dp)
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        modifier = Modifier
            .background(White)
            .padding(start = 7.dp, end = 8.dp),
        selectedContentColor = DeepGreen,
        unselectedContentColor = LightGray,
                onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.startDestinationId)
            }
        }
    )
}