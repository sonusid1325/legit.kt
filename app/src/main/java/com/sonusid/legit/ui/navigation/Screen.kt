package com.sonusid.legit.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    object Home : Screen()
    
    @Serializable
    object Profile : Screen()
}
