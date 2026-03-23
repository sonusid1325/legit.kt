package com.sonusid.legit.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sonusid.legit.ui.theme.LegitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                                .clickable { onBack() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Rounded.ArrowBack, contentDescription = "Back", tint = Color.Gray)
                        }
                    }
                },
                actions = {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(end = 16.dp)) {
                        Text("LEGIT", fontWeight = FontWeight.Black, fontSize = 24.sp, color = Color(0xFF6200EE), letterSpacing = 2.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            // Hero Profile Section
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(128.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Brush.linearGradient(listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)))
                            .padding(4.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp)).background(MaterialTheme.colorScheme.surfaceContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Rounded.Person, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(64.dp))
                        }
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(x = 8.dp, y = 8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Rounded.Verified, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(20.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Aditya Sharma", fontWeight = FontWeight.Bold, fontSize = 36.sp, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("SOVEREIGN LEVEL 4", color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 3.sp)
                }
            }

            // Stats Bento Grid
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(128.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                            .padding(24.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                            Icon(Icons.Rounded.Shield, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Column {
                                Text("IDENTITY STATUS", color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp)
                                Text("Verified", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(128.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                            .padding(24.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                            Icon(Icons.Rounded.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                            Column {
                                Text("VAULT SECURITY", color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp)
                                Text("Encrypted", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                    }
                }
            }

            // Settings Groups
            item {
                Column(verticalArrangement = Arrangement.spacedBy(48.dp)) {
                    // Security & Privacy
                    SettingsSection(title = "Security & Privacy", color = MaterialTheme.colorScheme.primary) {
                        SettingsRow(icon = Icons.Rounded.Fingerprint, title = "Biometric Lock", desc = "FaceID / Fingerprint active", isSwitch = true)
                        SettingsRow(icon = Icons.Rounded.Timer, title = "Session Timeout", desc = "2 Minutes of inactivity")
                    }
                    // Identity
                    SettingsSection(title = "Identity", color = MaterialTheme.colorScheme.secondary) {
                        SettingsRow(icon = Icons.Rounded.Badge, title = "Manage Documents", desc = "Passport, License, eID")
                    }
                    // App Settings
                    SettingsSection(title = "App Settings", color = MaterialTheme.colorScheme.surfaceBright) {
                        SettingsRow(icon = Icons.Rounded.NotificationsActive, title = "Notifications", desc = "Alerts for sensitive changes", isSwitch = true)
                        SettingsRow(icon = Icons.Rounded.DarkMode, title = "Dark Mode", desc = "Always on", isSwitch = true)
                    }
                }
            }

            // About
            item {
                Column(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(MaterialTheme.colorScheme.surfaceContainer).padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("LEGIT VAULT", fontWeight = FontWeight.Black, fontSize = 24.sp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), letterSpacing = 3.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Privacy Policy", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Terms of Sovereignty", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("VERSION 2.4.0 (TECHNICAL BUILD)", color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f), fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp)
                }
            }

            // Sign Out
            item {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp).border(1.dp, MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f), RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.1f), contentColor = MaterialTheme.colorScheme.error),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Terminate Session", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun SettingsSection(title: String, color: Color, content: @Composable () -> Unit) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(width = 4.dp, height = 24.dp).clip(CircleShape).background(color))
            Spacer(modifier = Modifier.width(8.dp))
            Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            content()
        }
    }
}

@Composable
fun SettingsRow(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, desc: String, isSwitch: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .clickable { /* TODO */ }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)).background(MaterialTheme.colorScheme.surfaceContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
                Text(desc, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
            }
        }
        if (isSwitch) {
            Box(
                modifier = Modifier.size(width = 48.dp, height = 24.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary).padding(4.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(modifier = Modifier.size(16.dp).clip(CircleShape).background(MaterialTheme.colorScheme.onPrimary))
            }
        } else {
            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
