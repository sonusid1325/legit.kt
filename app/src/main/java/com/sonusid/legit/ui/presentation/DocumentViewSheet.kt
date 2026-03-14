package com.sonusid.legit.ui.presentation

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fingerprint
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

@Composable
fun DocumentViewSheet(
    docType: String,
    onDismiss: () -> Unit
) {
    var isUnlocked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context as? FragmentActivity

    val executor = remember { ContextCompat.getMainExecutor(context) }
    
    // Setup Biometric Prompt
    val biometricPrompt = remember {
        activity?.let {
            BiometricPrompt(it, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    isUnlocked = true
                }
                
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    // Optional: handle error
                }
            })
        }
    }

    val promptInfo = remember {
        BiometricPrompt.PromptInfo.Builder()
            .setTitle("Legit Secure Vault")
            .setSubtitle("Confirm your screen lock to view $docType")
            // This allows Fingerprint, Face, PIN, Pattern, or Password
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .build()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isUnlocked) {
            SecurityLockContent(onUnlock = {
                biometricPrompt?.authenticate(promptInfo)
            })
        } else {
            DocHeader(docType)
            if (docType == "Aadhar") {
                AadharCardContent()
            } else if (docType == "PAN") {
                PanCardContent()
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = { /* TODO: Share Proof */ },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Rounded.Share, contentDescription = null)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Share Cryptographic Proof", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun DocHeader(title: String) {
    Text(
        text = "$title Card",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Black,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
fun SecurityLockContent(onUnlock: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(32.dp)
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            modifier = Modifier.size(90.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    Icons.Rounded.Lock,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            "Authentication Required",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center
        )
        
        Text(
            "Confirm your Screen Lock (Fingerprint, PIN, or Pattern) to reveal your sensitive documents.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp)
        )
        
        Spacer(modifier = Modifier.height(40.dp))
        
        Button(
            onClick = onUnlock,
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(Icons.Rounded.Fingerprint, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Unlock Vault", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun AadharCardContent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(220.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFDFD)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("AADHAR", fontWeight = FontWeight.Black, color = Color(0xFFD32F2F), fontSize = 20.sp)
                Text("Government of India", fontWeight = FontWeight.Bold, color = Color.Gray, fontSize = 12.sp)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // As requested: No name in Aadhar, updated DOB
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "DOB: 13/05/2005", 
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "GENDER: Male", 
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = "5432 1098 7654", 
                style = MaterialTheme.typography.headlineMedium.copy(letterSpacing = 4.sp),
                fontWeight = FontWeight.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}

@Composable
fun PanCardContent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(220.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(listOf(Color(0xFF00796B).copy(alpha = 0.1f), Color.Transparent))
        )) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("INCOME TAX DEPARTMENT", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = Color(0xFF004D40))
                    Text("GOVT. OF INDIA", fontWeight = FontWeight.Bold, fontSize = 11.sp, color = Color(0xFF004D40))
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = "FIROJ SIDDIQUIE", 
                    style = MaterialTheme.typography.headlineSmall, 
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF004D40)
                )
                
                Text(
                    text = "DOB: 13/05/2005", 
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF00695C),
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                Text(
                    text = "BJKPS1305S", 
                    style = MaterialTheme.typography.headlineMedium.copy(letterSpacing = 2.sp), 
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF004D40)
                )
                Text(
                    text = "Permanent Account Number", 
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF00695C)
                )
            }
        }
    }
}
