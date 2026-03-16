package com.sonusid.legit.ui.presentation

import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fingerprint
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.QrCode2
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
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
    val activity = remember(context) { context.findActivity() }
    val executor = remember(context) { ContextCompat.getMainExecutor(context) }

    val promptInfo = remember {
        BiometricPrompt.PromptInfo.Builder()
            .setTitle("Legit Secure Vault")
            .setSubtitle("Confirm your Screen Lock to view $docType")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .setConfirmationRequired(false)
            .build()
    }

    val biometricPrompt = remember {
        activity?.let {
            BiometricPrompt(it, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    isUnlocked = true
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    if (errorCode != BiometricPrompt.ERROR_USER_CANCELED) {
                        Toast.makeText(context, errString, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
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
            when (docType) {
                "Aadhar" -> AadharCardContent()
                "PAN" -> PanCardContent()
                "VoterID" -> VoterIDCardContent()
                "ABC" -> ABCCardContent()
                else -> Text("Document content not available")
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
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

fun Context.findActivity(): FragmentActivity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is FragmentActivity) return context
        context = context.baseContext
    }
    return null
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
            modifier = Modifier.size(100.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    Icons.Rounded.Lock,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            "Security Check",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center
        )
        
        Text(
            "Confirm your system authentication to reveal this document.",
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
            Text("Verify Identity", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun AadharCardContent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(240.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val path = Path().apply {
                    moveTo(0f, size.height * 0.7f)
                    quadraticBezierTo(size.width * 0.5f, size.height * 0.8f, size.width, size.height * 0.6f)
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                }
                drawPath(path, color = Color(0xFFFFF3E0))
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("भारतीय विशिष्ट पहचान प्राधिकरण", fontSize = 10.sp, color = Color(0xFFD32F2F), fontWeight = FontWeight.Bold)
                        Text("Unique Identification Authority of India", fontSize = 8.sp, color = Color.Gray)
                    }
                    Text("AADHAR", fontWeight = FontWeight.Black, color = Color(0xFFD32F2F), fontSize = 18.sp)
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 1.dp, color = Color(0xFFD32F2F).copy(alpha = 0.2f))

                Row(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF5F5F5))
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Rounded.Lock, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(40.dp))
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text("DOB: 13/05/2005", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                        Text("GENDER: Male / पुरुष", style = MaterialTheme.typography.bodyMedium)
                        
                        Spacer(modifier = Modifier.weight(1f))
                        
                        Icon(
                            Icons.Rounded.QrCode2, 
                            contentDescription = null, 
                            modifier = Modifier.size(50.dp).align(Alignment.End),
                            tint = Color.DarkGray
                        )
                    }
                }
            }
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color(0xFFD32F2F))
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "5432 1098 7654",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall.copy(letterSpacing = 6.sp),
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}

@Composable
fun PanCardContent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(240.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                for (i in 0..size.width.toInt() step 40) {
                    drawLine(
                        color = Color(0xFF004D40).copy(alpha = 0.05f),
                        start = Offset(i.toFloat(), 0f),
                        end = Offset(i.toFloat() + 100f, size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("आयकर विभाग", fontSize = 10.sp, color = Color(0xFF004D40), fontWeight = FontWeight.Bold)
                        Text("INCOME TAX DEPARTMENT", fontSize = 9.sp, color = Color(0xFF004D40), fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("भारत सरकार", fontSize = 10.sp, color = Color(0xFF004D40), fontWeight = FontWeight.Bold)
                        Text("GOVT. OF INDIA", fontSize = 9.sp, color = Color(0xFF004D40), fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("NAME / नाम", fontSize = 8.sp, color = Color(0xFF00695C))
                        Text("FIROJ SIDDIQUIE", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Black, color = Color(0xFF004D40))
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text("FATHER'S NAME / पिता का नाम", fontSize = 8.sp, color = Color(0xFF00695C))
                        Text("XXXX XXXX", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color(0xFF004D40))
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text("DATE OF BIRTH / जन्म तिथि", fontSize = 8.sp, color = Color(0xFF00695C))
                        Text("13/05/2005", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color(0xFF004D40))
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color.White.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                                .border(0.5.dp, Color(0xFF004D40).copy(alpha = 0.2f), RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Rounded.Lock, contentDescription = null, tint = Color(0xFF004D40).copy(alpha = 0.2f))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .width(70.dp)
                                .height(25.dp)
                                .background(Color.White.copy(alpha = 0.5f), RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("SIGNATURE", fontSize = 8.sp, color = Color.LightGray)
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text("स्थायी लेखा संख्या कार्ड", fontSize = 8.sp, color = Color(0xFF004D40))
                        Text("Permanent Account Number Card", fontSize = 8.sp, color = Color(0xFF004D40))
                        Text("BJKPS1305S", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black, color = Color(0xFF004D40), letterSpacing = 2.sp)
                    }
                    Icon(Icons.Rounded.QrCode2, contentDescription = null, modifier = Modifier.size(40.dp), tint = Color(0xFF004D40))
                }
            }
        }
    }
}

@Composable
fun VoterIDCardContent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(240.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("ELECTION COMMISSION OF INDIA", fontWeight = FontWeight.Bold, fontSize = 10.sp, color = Color(0xFF2E7D32))
                Text("VOTER ID", fontWeight = FontWeight.Black, color = Color(0xFF2E7D32), fontSize = 14.sp)
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFF2E7D32).copy(alpha = 0.3f))
            
            Row {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White, RoundedCornerShape(4.dp))
                        .border(1.dp, Color(0xFF2E7D32).copy(alpha = 0.1f), RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Rounded.Lock, contentDescription = null, tint = Color.LightGray)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("NAME: FIROJ SIDDIQUIE", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("FATHER'S NAME: XXXX", fontSize = 12.sp)
                    Text("GENDER: MALE", fontSize = 12.sp)
                    Text("DOB: 13/05/2005", fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "ABC1234567",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color(0xFF1B5E20)
            )
        }
    }
}

@Composable
fun ABCCardContent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(240.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ACADEMIC BANK OF CREDITS", fontWeight = FontWeight.Black, color = Color(0xFF3F51B5), fontSize = 14.sp)
            Text("Ministry of Education, Government of India", fontSize = 10.sp, color = Color.Gray)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Rounded.Lock, contentDescription = null, tint = Color.LightGray)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("FIROJ SIDDIQUIE", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("ABC ID: 123-456-789-012", color = Color(0xFF3F51B5), fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("TOTAL CREDITS", fontSize = 10.sp, color = Color.Gray)
                    Text("142", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Black, color = Color(0xFF3F51B5))
                }
                Icon(Icons.Rounded.QrCode2, contentDescription = null, modifier = Modifier.size(60.dp), tint = Color(0xFF3F51B5))
            }
        }
    }
}
