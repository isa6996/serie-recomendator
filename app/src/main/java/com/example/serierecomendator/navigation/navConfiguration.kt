package com.example.serierecomendator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

val AUTH_GRAPH_ROUTE = "login"
val SETTINGS_GRAPH_ROUTE = "settings"
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AUTH_GRAPH_ROUTE
    ) {
        addNavGraph(
            navController = navController,
            startDestination = Screen.Login.route,
            route = AUTH_GRAPH_ROUTE,
            screens = listOf(Screen.Login, Screen.SignUp, Screen.ForgottenPass, Screen.Home)
        )
        addNavGraph(
            navController = navController,
            startDestination = Screen.Settings.route,
            route = SETTINGS_GRAPH_ROUTE,
            screens = listOf(Screen.Settings, Screen.Search, Screen.Profile, Screen.EditProfile, Screen.Home)
        )
    }
}
