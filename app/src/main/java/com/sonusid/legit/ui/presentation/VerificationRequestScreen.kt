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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sonusid.legit.ui.theme.LegitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationRequestScreen() {
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
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(end = 16.dp)) {
                        Text("VAULT", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Gray, letterSpacing = 2.sp)
                        Text("ACTIVITY", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Gray, letterSpacing = 2.sp)
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                                .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f), CircleShape)
                                .clickable { /* TODO */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Rounded.Person, contentDescription = "Profile", tint = Color.Gray)
                        }
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
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            item {
                Column {
                    Text("SECURITY PROTOCOL", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 2.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Verification Request", fontWeight = FontWeight.Black, fontSize = 48.sp, color = MaterialTheme.colorScheme.onSurface, lineHeight = 56.sp)
                    Spacer(modifier = Modifier.height(24.dp))

                    // Requestor Card
                    Row(
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(MaterialTheme.colorScheme.surfaceContainer).padding(32.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(64.dp).clip(RoundedCornerShape(12.dp)).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)), contentAlignment = Alignment.Center) {
                                Icon(Icons.Rounded.AccountBalance, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(32.dp))
                            }
                            Spacer(modifier = Modifier.width(24.dp))
                            Column {
                                Text("REQUESTOR", color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                                Text("Standard Ledger Inc.", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                        Box(modifier = Modifier.clip(RoundedCornerShape(50)).background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)).padding(horizontal = 12.dp, vertical = 4.dp)) {
                            Text("Verified Entity", color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 1.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Purpose Block
                    Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(MaterialTheme.colorScheme.surfaceContainerHigh).padding(4.dp)) {
                        Column(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).background(MaterialTheme.colorScheme.surface).padding(24.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Rounded.Info, contentDescription = null, tint = MaterialTheme.colorScheme.tertiary, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Purpose of Access", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface)
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text("To authorize the final settlement of contract #KT-992-DELTA and confirm zero-knowledge proof of sovereign identity for multi-sig execution.", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 16.sp, lineHeight = 24.sp)
                        }
                    }
                }
            }

            item {
                Column {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
                        Text("Requested Fields", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
                        Text("3 ITEMS", color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 2.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    FieldRow("Digital Signature Hash", "SHA-256 Protocol", Icons.Rounded.Fingerprint)
                    Spacer(modifier = Modifier.height(16.dp))
                    FieldRow("Immutable Ledger Key", "Contract Reference", Icons.Rounded.HistoryEdu)
                    Spacer(modifier = Modifier.height(16.dp))
                    FieldRow("Proof of Solvency", "ZKP Verification", Icons.Rounded.VerifiedUser)
                }
            }

            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("REQUEST EXPIRATION", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Black, fontSize = 12.sp, letterSpacing = 3.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text("23:59", fontWeight = FontWeight.Black, fontSize = 96.sp, color = Color.White, letterSpacing = (-2).sp)
                        Text("58", fontWeight = FontWeight.Black, fontSize = 24.sp, color = MaterialTheme.colorScheme.error)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(4.dp).clip(CircleShape).background(MaterialTheme.colorScheme.surfaceContainerHighest)) {
                        Box(modifier = Modifier.fillMaxWidth().height(4.dp).clip(CircleShape).background(MaterialTheme.colorScheme.error))
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier.fillMaxWidth().height(64.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Rounded.Verified, contentDescription = null, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("APPROVE REQUEST", fontWeight = FontWeight.Black, fontSize = 20.sp, letterSpacing = 2.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = MaterialTheme.colorScheme.outline),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("REJECT & REVOKE ACCESS", fontWeight = FontWeight.Bold, fontSize = 16.sp, letterSpacing = 2.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun FieldRow(title: String, desc: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
                Text(desc, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.sp)
            }
        }
        Icon(Icons.Rounded.LockOpen, contentDescription = null, tint = MaterialTheme.colorScheme.primaryContainer, modifier = Modifier.size(24.dp))
    }
}
