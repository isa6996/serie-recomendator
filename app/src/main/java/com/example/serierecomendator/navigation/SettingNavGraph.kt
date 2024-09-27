package com.example.serierecomendator.navigation

import ProfileView
import SearchView
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.serierecomendator.view.EditProfileView
import com.example.serierecomendator.view.ForgottenPassView
import com.example.serierecomendator.view.LoginView
import com.example.serierecomendator.view.RecomendationListView
import com.example.serierecomendator.view.SettingsView
import com.example.serierecomendator.view.SignUpView
import com.example.serierecomendator.viewModel.RecomendationListViewModel

fun NavGraphBuilder.addNavGraph(
    navController: NavHostController,
    startDestination: String,
    route: String,
    screens: List<Screen>
) {
    navigation(
        startDestination = startDestination,
        route = route
    ) {
        screens.forEach { screen ->
           composable(screen.route){
               when (screen) {
                   is Screen.Login -> LoginView(navController)
                   is Screen.Home -> RecomendationListView(navController)
                   is Screen.SignUp -> SignUpView(navController)
                   is Screen.ForgottenPass -> ForgottenPassView(navController)
                   is Screen.Settings -> SettingsView(navController)
                   is Screen.Profile -> ProfileView(navController)
                   is Screen.EditProfile -> EditProfileView(navController)
                   is Screen.Search -> SearchView(navController)
                   else -> {}
               }
           }
        }
    }
}

