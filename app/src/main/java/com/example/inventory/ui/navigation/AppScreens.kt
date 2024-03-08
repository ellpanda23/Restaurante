package com.example.inventory.ui.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen: AppScreens("login_screen")
    object HomeScreen: AppScreens("home_screen")
    object AlumnoInfoScreen: AppScreens("alumno_info_screen")
    //object LoginScreen: AppScreens("login_screen")
}