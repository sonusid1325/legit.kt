package com.sonusid.legit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sonusid.legit.ui.presentation.MainScreen
import com.sonusid.legit.ui.presentation.VerificationRequestScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen()
        }
        composable("verification") {
            VerificationRequestScreen()
        }
    }
}

// Added to end, but let's properly edit NavGraph.kt
