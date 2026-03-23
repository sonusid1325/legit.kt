package com.sonusid.legit.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    outline = Outline,
    onPrimary = OnPrimary,
    primary = Primary,
    outlineVariant = OutlineVariant,
    primaryContainer = PrimaryContainer,
    onTertiaryContainer = OnTertiaryContainer,
    tertiaryContainer = TertiaryContainer,
    onTertiary = OnTertiary,
    onSecondary = OnSecondary,
    inverseOnSurface = InverseOnSurface,
    onBackground = OnBackground,
    secondary = Secondary,
    inverseSurface = InverseSurface,
    surface = Surface,
    onSecondaryContainer = OnSecondaryContainer,
    inversePrimary = InversePrimary,
    onErrorContainer = OnErrorContainer,
    error = Error,
    background = Background,
    onSurfaceVariant = OnSurfaceVariant,
    onPrimaryContainer = OnPrimaryContainer,
    secondaryContainer = SecondaryContainer,
    tertiary = Tertiary,
    surfaceVariant = SurfaceVariant,
    onSurface = OnSurface,
    surfaceTint = SurfaceTint,
    onError = OnError,
    errorContainer = ErrorContainer
)

// In this app we only use dark mode as per design, but keeping light colorscheme to compile
private val LightColorScheme = lightColorScheme(
    outline = Outline,
    onPrimary = OnPrimary,
    primary = Primary,
    outlineVariant = OutlineVariant,
    primaryContainer = PrimaryContainer,
    onTertiaryContainer = OnTertiaryContainer,
    tertiaryContainer = TertiaryContainer,
    onTertiary = OnTertiary,
    onSecondary = OnSecondary,
    inverseOnSurface = InverseOnSurface,
    onBackground = OnBackground,
    secondary = Secondary,
    inverseSurface = InverseSurface,
    surface = Surface,
    onSecondaryContainer = OnSecondaryContainer,
    inversePrimary = InversePrimary,
    onErrorContainer = OnErrorContainer,
    error = Error,
    background = Background,
    onSurfaceVariant = OnSurfaceVariant,
    onPrimaryContainer = OnPrimaryContainer,
    secondaryContainer = SecondaryContainer,
    tertiary = Tertiary,
    surfaceVariant = SurfaceVariant,
    onSurface = OnSurface,
    surfaceTint = SurfaceTint,
    onError = OnError,
    errorContainer = ErrorContainer
)

@Composable
fun LegitTheme(
    darkTheme: Boolean = true, // Force Dark mode as per design
    dynamicColor: Boolean = false, // Disable dynamic colors to stick with our branding
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
