package com.sonusid.legit.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Rule
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegitTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onNotificationClick: () -> Unit,
    onVerificationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val collapsedFraction = scrollBehavior.state.collapsedFraction
    
    LargeTopAppBar(
        title = {
            Column {
                Text(
                    text = "Legit",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Black
                )
                if (collapsedFraction < 0.4f) {
                    Text(
                        text = "Your Private Verification Pipeline",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 1f - (collapsedFraction * 2.5f)),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onVerificationClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.Rule,
                    contentDescription = "Verification Requests",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = onNotificationClick) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notifications",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = onProfileClick) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "Profile",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    )
}
