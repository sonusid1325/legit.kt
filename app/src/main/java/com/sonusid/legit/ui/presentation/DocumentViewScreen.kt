package com.sonusid.legit.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Fingerprint
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class DocType { AADHAR, PAN }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentViewScreen(
    docType: DocType,
    onBack: () -> Unit
) {
    var isUnlocked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (docType == DocType.AADHAR) "Aadhar Card" else "PAN Card", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (isUnlocked) {
                        IconButton(onClick = { /* Share Proof */ }) {
                            Icon(Icons.Rounded.Share, contentDescription = "Share Proof")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (!isUnlocked) {
                SecurityLockScreen(onUnlock = { isUnlocked = true })
            } else {
                if (docType == DocType.AADHAR) {
                    AadharCardView()
                } else {
                    PanCardView()
                }
            }
        }
    }
}

@Composable
fun SecurityLockScreen(onUnlock: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(32.dp)
    ) {
        Icon(
            Icons.Rounded.Fingerprint,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Authentication Required",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Please use fingerprint to view secure document",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 8.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onUnlock,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text("Authenticate", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun AadharCardView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("AADHAR", fontWeight = FontWeight.Black, color = Color.Red, fontSize = 18.sp)
                Text("Government of India", fontWeight = FontWeight.Bold, color = Color.DarkGray)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("Firoj Khan", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("DOB: 01/01/1990", style = MaterialTheme.typography.bodyMedium)
            Text("GENDER: Male", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.weight(1f))
            Text("1234 5678 9012", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, letterSpacing = 4.sp)
        }
    }
}

@Composable
fun PanCardView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(listOf(Color(0xFF00796B).copy(alpha = 0.1f), Color.Transparent))
        )) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("INCOME TAX DEPARTMENT", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Text("GOVT. OF INDIA", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text("FIROJ KHAN", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
                Text("FATHER NAME: XXXX", style = MaterialTheme.typography.labelSmall)
                Text("DOB: 01/01/1990", style = MaterialTheme.typography.labelSmall)
                Spacer(modifier = Modifier.weight(1f))
                Text("ABCDE1234F", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                Text("Permanent Account Number", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
