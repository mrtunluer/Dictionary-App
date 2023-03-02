package com.mertdev.dictionaryapp.utils.bottom_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mertdev.dictionaryapp.presentation.Screen
import com.mertdev.dictionaryapp.presentation.saved_words.SavedWordsScreen
import com.mertdev.dictionaryapp.presentation.word_data.WordDataScreen

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        Screen.WordDataScreen, Screen.SavedWordsScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen, currentDestination: NavDestination?, navController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = { Icon(imageVector = screen.icon, contentDescription = "Navigation ID") },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        },
        alwaysShowLabel = false
    )
}

@Composable
fun BottomNavGraph(
    modifier: Modifier, navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.WordDataScreen.route
    ) {
        composable(route = Screen.WordDataScreen.route) {
            WordDataScreen()
        }
        composable(route = Screen.SavedWordsScreen.route) {
            SavedWordsScreen()
        }
    }
}