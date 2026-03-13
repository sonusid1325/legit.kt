package com.sonusid.legit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sonusid.legit.ui.presentation.HomeScreen
import com.sonusid.legit.ui.theme.LegitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LegitTheme {
                HomeScreen()
            }
        }
    }
}
