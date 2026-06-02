package com.example.medreminder.ui.navigation

sealed class Screen(val route: String) {
    object Onboard : Screen("onboard")
    object Login : Screen("login")
    object Home : Screen("home")
}