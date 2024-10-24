package com.example.serierecomendator.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.example.serierecomendator.view.MoreInfoView
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

val AUTH_GRAPH_ROUTE = "login"
val SETTINGS_GRAPH_ROUTE = "settings"
@RequiresApi(Build.VERSION_CODES.O)
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
            startDestination = Screen.Search.route,
            route = SETTINGS_GRAPH_ROUTE,
            screens = listOf(Screen.Settings, Screen.Search, Screen.Profile, Screen.EditProfile, Screen.Home)
        )
        composable(
            route = "movie_details_screen/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) {
            val movieId = it.arguments?.getString("movieId") ?: ""
            MoreInfoView(movieId = movieId)
        }
    }
}

