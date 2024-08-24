package com.example.serierecomendator.navigation

sealed class Screen(
    val route: String
) {
    object Login : Screen("login_screen")
    object Home : Screen("home_page")
    object ForgottenPass : Screen("forgottenPass_screen")
    object SignUp : Screen("signUp_screen")
    object Profile : Screen("profile_screen")
    object Settings : Screen("settings_screen")
    object EditProfile : Screen("edit_profile")
}