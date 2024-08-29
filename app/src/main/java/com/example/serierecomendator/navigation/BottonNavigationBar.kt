package com.example.serierecomendator.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Home,
        Screen.Profile,
        Screen.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (shouldShowBottomBar(currentRoute)) {

        BottomNavigation {
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        when (screen) {
                            is Screen.Home -> Icon(
                                Icons.Default.Email,
                                contentDescription = null
                            )
                            is Screen.Settings -> Icon(
                                Icons.Default.Settings,
                                contentDescription = null
                            )
                            is Screen.Profile -> Icon(
                                Icons.Default.Person,
                                contentDescription = null
                            )
                            else -> Icon(Icons.Default.Info, contentDescription = null)
                        }
                    },
                    label = { Text(screen.route) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

fun shouldShowBottomBar(route: String?): Boolean {
    return route == Screen.Profile.route || route == Screen.Settings.route|| route == Screen.Home.route
}