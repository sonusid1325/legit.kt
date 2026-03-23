package com.sonusid.legit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.icons.automirrored.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sonusid.legit.ui.theme.LegitTheme

@Composable
fun NotificationSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text("Notifications", fontWeight = FontWeight.Bold, fontSize = 28.sp, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(4.dp))
                Text("3 unread security alerts", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text(
                "MARK ALL READ",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 2.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Notifications List
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            NotificationItem(
                title = "New Verification Request from Amazon",
                desc = "A sign-in attempt was detected from a new device in Seattle, WA. Action required.",
                time = "JUST NOW",
                icon = Icons.Rounded.VerifiedUser,
                iconColor = MaterialTheme.colorScheme.primary,
                iconBg = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
                bg = MaterialTheme.colorScheme.surfaceContainerHigh,
                actions = {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 12.dp)) {
                        Button(
                            onClick = { /* TODO */ },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text("APPROVE", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                        Button(
                            onClick = { /* TODO */ },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest, contentColor = MaterialTheme.colorScheme.onSurface),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text("DENY", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }
            )

            NotificationItem(
                title = "Biometric Login Successful",
                desc = "Your vault was accessed using FaceID. No further action is necessary.",
                time = "12M AGO",
                icon = Icons.Rounded.Fingerprint,
                iconColor = MaterialTheme.colorScheme.secondary,
                iconBg = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f),
                bg = MaterialTheme.colorScheme.surfaceContainer
            )

            NotificationItem(
                title = "Identity Document Expiring Soon",
                desc = "Your Passport (ID: ****8921) will expire in 14 days. Re-verify to maintain access levels.",
                time = "2H AGO",
                icon = Icons.Rounded.Error,
                iconColor = MaterialTheme.colorScheme.error,
                iconBg = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f),
                bg = MaterialTheme.colorScheme.surfaceContainer,
                actions = {
                    Row(modifier = Modifier.padding(top = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("UPDATE NOW", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.AutoMirrored.Rounded.ArrowForward, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                    }
                }
            )

            // Past section
            Text("YESTERDAY", color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
            NotificationItem(
                title = "Vault Cloud Sync Complete",
                desc = "All encrypted fragments have been verified and mirrored to secure relays.",
                time = "24H+",
                icon = Icons.Rounded.Sync,
                iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                iconBg = MaterialTheme.colorScheme.surfaceContainerHighest,
                bg = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.5f),
                isDimmed = true
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Bottom Action Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.History, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.width(12.dp))
                Text("VIEW FULL HISTORY", color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold, fontSize = 14.sp, letterSpacing = 2.sp)
            }
        }
    }
}

@Composable
fun NotificationItem(
    title: String, desc: String, time: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector, iconColor: Color, iconBg: Color, bg: Color,
    isDimmed: Boolean = false, actions: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier.size(48.dp).clip(RoundedCornerShape(8.dp)).background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(24.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.weight(1f), maxLines = 1)
                Spacer(modifier = Modifier.width(8.dp))
                Text(time, color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 1.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(desc, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp, lineHeight = 20.sp)
            if (actions != null) {
                actions()
            }
        }
    }
}
