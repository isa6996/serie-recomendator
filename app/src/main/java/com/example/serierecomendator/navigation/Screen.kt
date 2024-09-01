package com.example.serierecomendator.navigation

sealed class Screen(
    val route: String
) {
    object Login : Screen("login screen")
    object Home : Screen("home page")
    object ForgottenPass : Screen("forgottenPass screen")
    object SignUp : Screen("signUp screen")
    object Profile : Screen("profile screen")
    object Settings : Screen("settings screen")
    object EditProfile : Screen("edit profile")
}