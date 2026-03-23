package com.sonusid.legit.ui.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun VaultScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.Security,
                            contentDescription = "Logo",
                            tint = Color(0xFF6200EE),
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "LEGIT.KT",
                            fontWeight = FontWeight.Black,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF6200EE),
                            letterSpacing = 2.sp
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape)
                            .clickable { /* TODO */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Rounded.Person, contentDescription = "Profile", tint = Color.Gray)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Vault Protocol Header
            Column {
                Text(
                    text = "SECURE STORAGE",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Vault Protocol",
                            fontWeight = FontWeight.Black,
                            fontSize = 48.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 56.sp
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "End-to-end encrypted assets secured by quantum-resistant algorithms. Your data remains local and inaccessible to third parties.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 16.sp,
                            lineHeight = 24.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.surfaceContainer)
                                .border(width = 4.dp, color = MaterialTheme.colorScheme.primary)
                                .padding(horizontal = 24.dp, vertical = 16.dp)
                        ) {
                            Column {
                                Text("STATUS", color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp)
                                Text("SHIELD ACTIVE", color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            }
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.surfaceContainer)
                                .padding(horizontal = 24.dp, vertical = 16.dp)
                        ) {
                            Column {
                                Text("INTEGRITY", color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp)
                                Text("99.9%", color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Document Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 80.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                // Add Asset CTA
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(4f/3f)
                            .clip(RoundedCornerShape(24.dp))
                            .background(Brush.linearGradient(listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondaryContainer)))
                            .clickable { /* TODO */ }
                            .padding(32.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(MaterialTheme.colorScheme.onPrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Rounded.Add, contentDescription = "Add", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(36.dp))
                            }
                            Column {
                                Text("ADD\nASSET", fontWeight = FontWeight.Black, fontSize = 36.sp, color = MaterialTheme.colorScheme.onPrimary, lineHeight = 36.sp)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text("INITIALIZE NEW ENCRYPTION", color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f), fontWeight = FontWeight.Bold, fontSize = 14.sp, letterSpacing = 1.sp)
                            }
                        }
                    }
                }

                // Encrypted Cards
                item {
                    EncryptedCard(
                        type = "IDENTIFICATION", title = "Biometric_Hash_v2.kt",
                        date = "Oct 24, 2023", size = "4.2 MB",
                        icon = Icons.Rounded.Fingerprint, lockIcon = Icons.Rounded.Lock
                    )
                }
                item {
                    EncryptedCard(
                        type = "FINANCIAL", title = "Ledger_Export_Final.csv",
                        date = "Nov 02, 2023", size = "128 KB",
                        icon = Icons.Rounded.AccountBalanceWallet, lockIcon = Icons.Rounded.Shield
                    )
                }
                item {
                    EncryptedCard(
                        type = "LEGAL", title = "Smart_Contract_A8.pdf",
                        date = "Sep 15, 2023", size = "1.8 MB",
                        icon = Icons.Rounded.Gavel, lockIcon = Icons.Rounded.Lock
                    )
                }
                item {
                    EncryptedCard(
                        type = "RECOVERY", title = "Master_Seed_Phrase.txt",
                        date = "Aug 20, 2023", size = "2 KB",
                        icon = Icons.Rounded.Key, lockIcon = Icons.Rounded.Lock
                    )
                }
                item {
                    EncryptedCard(
                        type = "PROPERTY", title = "Deed_Registry_09.png",
                        date = "Oct 30, 2023", size = "12.4 MB",
                        icon = Icons.Rounded.HomeWork, lockIcon = Icons.Rounded.Lock
                    )
                }
            }
        }
    }
}

@Composable
fun EncryptedCard(
    type: String, title: String, date: String, size: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    lockIcon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4f/3f)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .clickable { /* TODO */ }
            .padding(32.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Icon(lockIcon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(36.dp))
            }
            Column {
                Text(type, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(title, fontWeight = FontWeight.Bold, fontSize = 24.sp, color = MaterialTheme.colorScheme.onSurface, lineHeight = 28.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(date, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(MaterialTheme.colorScheme.outlineVariant))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(size, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp)
                }
            }
        }
    }
}
