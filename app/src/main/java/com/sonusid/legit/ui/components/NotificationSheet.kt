package com.sonusid.legit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class NotificationItem(
    val id: Int,
    val title: String,
    val description: String,
    val time: String,
    val icon: ImageVector,
    val color: androidx.compose.ui.graphics.Color
)

@Composable
fun NotificationSheetContent() {
    val notifications = listOf(
        NotificationItem(
            1, "Verification Successful", "Your Aadhar has been verified by Bank of India.", "2m ago",
            Icons.Rounded.Verified, MaterialTheme.colorScheme.primary
        ),
        NotificationItem(
            2, "Security Alert", "New login detected from a Chrome browser.", "1h ago",
            Icons.Rounded.Security, MaterialTheme.colorScheme.error
        ),
        NotificationItem(
            3, "New Request", "Employer 'TechCorp' requested document verification.", "3h ago",
            Icons.Rounded.Notifications, MaterialTheme.colorScheme.secondary
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        Text(
            text = "Notifications",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )
        
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(notifications) { notification ->
                NotificationRow(notification)
            }
        }
    }
}

@Composable
fun NotificationRow(notification: NotificationItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(notification.color.copy(alpha = 0.1f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = notification.icon,
                contentDescription = null,
                tint = notification.color,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = notification.time,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = notification.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
