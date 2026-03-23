package com.sonusid.legit.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sonusid.legit.ui.theme.LegitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScreen() {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Hero Header
            item {
                Column {
                    Text("IMMUTABLE LOG", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 3.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text("Activity", fontWeight = FontWeight.Bold, fontSize = 64.sp, color = MaterialTheme.colorScheme.onSurface, lineHeight = 64.sp)
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                                    .padding(horizontal = 24.dp, vertical = 12.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Rounded.FilterList, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text("FILTER LOGS", fontWeight = FontWeight.Bold, fontSize = 14.sp, letterSpacing = 1.sp)
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(MaterialTheme.colorScheme.primary)
                                    .padding(horizontal = 24.dp, vertical = 12.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Rounded.Download, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text("EXPORT", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onPrimary, letterSpacing = 1.sp)
                                }
                            }
                        }
                    }
                }
            }

            // Today
            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Today", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(modifier = Modifier.weight(1f).height(2.dp).background(MaterialTheme.colorScheme.surfaceContainer))
                    }

                    AuditTrailCard(
                        title = "Biometric Authentication", desc = "Hardware-level passkey validation for Vault access",
                        hash = "HASH: 7f8e...2a1b", time = "14:22 UTC", status = "VERIFIED",
                        icon = Icons.Rounded.Fingerprint, iconColor = MaterialTheme.colorScheme.primary,
                        statusBg = Color(0xFF1a3a1a), statusColor = Color(0xFF4ade80)
                    )

                    AuditTrailCard(
                        title = "Protocol Upgrade", desc = "Migration to Quantum-Resistant algorithm 1.4.0",
                        hash = "HASH: 0x22...f9e1", time = "09:12 UTC", status = "PENDING",
                        icon = Icons.Rounded.VpnKey, iconColor = MaterialTheme.colorScheme.secondary,
                        statusBg = Color(0xFF3a301a), statusColor = Color(0xFFfacc15)
                    )
                }
            }

            // Yesterday
            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Yesterday", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(modifier = Modifier.weight(1f).height(2.dp).background(MaterialTheme.colorScheme.surfaceContainer))
                    }

                    AuditTrailCard(
                        title = "Data Sovereignty Backup", desc = "Cold-storage encryption sequence finalized",
                        hash = "HASH: d4c1...88ab", time = "22:45 UTC", status = "APPROVED",
                        icon = Icons.Rounded.CloudUpload, iconColor = MaterialTheme.colorScheme.primary,
                        statusBg = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f), statusColor = MaterialTheme.colorScheme.primaryContainer
                    )

                    AuditTrailCard(
                        title = "Unauthorized API Call", desc = "Endpoint /v1/secrets/raw blocked by firewall policy",
                        hash = "HASH: e01a...bb02", time = "18:30 UTC", status = "REJECTED",
                        icon = Icons.Rounded.Report, iconColor = MaterialTheme.colorScheme.error,
                        statusBg = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f), statusColor = MaterialTheme.colorScheme.error
                    )

                    AuditTrailCard(
                        title = "Ephemeral Token Cleanup", desc = "Automatic revocation of 15-minute access grants",
                        hash = "HASH: 3c9b...ff10", time = "04:00 UTC", status = "EXPIRED",
                        icon = Icons.Rounded.TimerOff, iconColor = MaterialTheme.colorScheme.outline,
                        statusBg = MaterialTheme.colorScheme.surfaceContainerHighest, statusColor = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}

@Composable
fun AuditTrailCard(
    title: String, desc: String, hash: String, time: String, status: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector, iconColor: Color,
    statusBg: Color, statusColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.Top, modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier.size(56.dp).clip(RoundedCornerShape(16.dp)).background(MaterialTheme.colorScheme.surfaceContainerHighest),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(32.dp))
                }
                Spacer(modifier = Modifier.width(24.dp))
                Column {
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(desc, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)).padding(horizontal = 8.dp, vertical = 4.dp)) {
                            Text(hash, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f), fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("• $time", color = MaterialTheme.colorScheme.outline, fontSize = 10.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier.clip(CircleShape).background(statusBg).padding(horizontal = 16.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(status, color = statusColor, fontWeight = FontWeight.Black, fontSize = 10.sp, letterSpacing = 2.sp)
            }
        }
    }
}
