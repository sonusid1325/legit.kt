package com.sonusid.legit.ui.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sonusid.legit.ui.components.*
import com.sonusid.legit.ui.theme.LegitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProfileClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    
    // Entry animation state
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                                .border(2.dp, Color(0xFF6200EE), CircleShape)
                                .clickable { onProfileClick() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Rounded.Person, contentDescription = "Profile", tint = Color.Gray)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                scrollBehavior = scrollBehavior
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
            // Hero Section: Dynamic Balance & Status
            item {
                AnimatedEntryItem(visible = visible, index = 0) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(32.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                            .padding(32.dp)
                    ) {
                        Text(
                            text = "TOTAL SECURED ASSETS",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 3.sp,
                            fontSize = 10.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "$2.48",
                                fontWeight = FontWeight.Black,
                                fontSize = 64.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.displayLarge
                            )
                            Text(
                                text = "M",
                                fontWeight = FontWeight.Bold,
                                fontSize = 64.sp,
                                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                style = MaterialTheme.typography.displayLarge,
                                modifier = Modifier.padding(bottom = 0.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(48.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                                    .padding(horizontal = 24.dp, vertical = 16.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary))
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text("Vault Nodes Online", fontWeight = FontWeight.Medium, fontSize = 14.sp)
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                                    .padding(horizontal = 24.dp, vertical = 16.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Rounded.Fingerprint, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text("Biometrics: Active", fontWeight = FontWeight.Medium, fontSize = 14.sp)
                                }
                            }
                        }
                    }
                }
            }

            // Pending Approvals Highlight
            item {
                AnimatedEntryItem(visible = visible, index = 1) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(32.dp))
                            .background(Brush.linearGradient(listOf(MaterialTheme.colorScheme.primary, Color(0xFF6200EE))))
                            .padding(32.dp)
                    ) {
                        Icon(Icons.Rounded.PriorityHigh, contentDescription = null, tint = Color.White, modifier = Modifier.size(36.dp))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("3 Pending Approvals", fontWeight = FontWeight.Bold, fontSize = 28.sp, color = Color.White)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Action required for secure cross-chain transfers.", color = Color.White.copy(alpha = 0.8f))
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(
                            onClick = { /* TODO */ },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF6200EE)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Review Now", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }
                }
            }

            // Requires Attention
            item {
                AnimatedEntryItem(visible = visible, index = 2) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text("Requires Attention", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                            Text("VIEW ALL", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp)
                        }
                        Spacer(modifier = Modifier.height(24.dp))

                        RequiresAttentionCard(
                            title = "Key Rotation Request",
                            desc = "Secondary hardware module requesting master key rotation cycle.",
                            type = "HIGH RISK",
                            icon = Icons.Rounded.LockReset,
                            color = MaterialTheme.colorScheme.error,
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            btn1 = "Deny", btn2 = "Authorize"
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        RequiresAttentionCard(
                            title = "Cold Storage Outbound",
                            desc = "Transfer of 12.50 ETH to whitelist address: 0x4f...892",
                            type = "WITHDRAWAL",
                            icon = Icons.Rounded.AccountBalanceWallet,
                            color = MaterialTheme.colorScheme.primary,
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            btn1 = "Cancel", btn2 = "Approve"
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        RequiresAttentionCard(
                            title = "New Delegate Request",
                            desc = "Invite for user 'Alex_Dev' as a restricted vault signatory.",
                            type = "IDENTITY",
                            icon = Icons.Rounded.PersonAdd,
                            color = MaterialTheme.colorScheme.secondary,
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            btn1 = "Reject", btn2 = "Accept"
                        )
                    }
                }
            }

            // Recent Activity
            item {
                AnimatedEntryItem(visible = visible, index = 3) {
                    Column {
                        Text("Recent Activity", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                        Spacer(modifier = Modifier.height(16.dp))

                        ActivityRow(
                            title = "Network Scan Completed",
                            desc = "0 Threats detected across 14 nodes",
                            time = "14:02 PM",
                            tag = "VERIFIED",
                            icon = Icons.Rounded.Analytics,
                            iconColor = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ActivityRow(
                            title = "Biometric Re-auth",
                            desc = "Successful facial verification for vault entry",
                            time = "11:45 AM",
                            tag = "SECURE",
                            icon = Icons.Rounded.Shield,
                            iconColor = MaterialTheme.colorScheme.error,
                            isDimmed = true
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ActivityRow(
                            title = "Protocol Update",
                            desc = "System-wide encryption v4.2.0 deployed",
                            time = "09:12 AM",
                            tag = "SYSTEM",
                            icon = Icons.Rounded.Settings,
                            iconColor = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RequiresAttentionCard(
    title: String, desc: String, type: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color, containerColor: Color,
    btn1: String, btn2: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        // Left Border
        Box(modifier = Modifier.width(4.dp).matchParentSize().background(color))

        Column(modifier = Modifier.padding(32.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(containerColor).padding(12.dp)) {
                    Icon(icon, contentDescription = null, tint = color)
                }
                Text(type, color = color, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(desc, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.weight(1f).height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest, contentColor = MaterialTheme.colorScheme.onSurface),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(btn1, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.weight(1f).height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = MaterialTheme.colorScheme.onSurface),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(btn2, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ActivityRow(
    title: String, desc: String, time: String, tag: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color, isDimmed: Boolean = false
) {
    val bg = if (isDimmed) MaterialTheme.colorScheme.surfaceContainerLow else MaterialTheme.colorScheme.surfaceContainer
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(48.dp).clip(CircleShape).background(MaterialTheme.colorScheme.surfaceContainerHighest),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconColor)
            }
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(desc, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
            }
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(time, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(tag, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 2.sp)
        }
    }
}

@Composable
fun AnimatedEntryItem(
    visible: Boolean,
    index: Int,
    content: @Composable () -> Unit
) {
    val animationDelay = index * 100
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 500, delayMillis = animationDelay),
        label = "alpha"
    )
    val translateY by animateFloatAsState(
        targetValue = if (visible) 0f else 50f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "translateY"
    )

    Box(
        modifier = Modifier
            .graphicsLayer(
                alpha = alpha,
                translationY = translateY
            )
    ) {
        content()
    }
}
