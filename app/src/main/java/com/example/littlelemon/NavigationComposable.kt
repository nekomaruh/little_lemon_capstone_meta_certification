package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.preference.PreferenceManager

@Composable
fun Navigation(navController: NavHostController) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(LocalContext.current)
    val userIsLogged = prefs.getBoolean("userIsLogged", true)

    NavHost(
        navController = navController,
        startDestination = if (userIsLogged) Home.route else Onboarding.route
    ) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            Home()
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}