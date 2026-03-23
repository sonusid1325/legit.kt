package com.sonusid.legit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ConfirmationDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    companyName: String = "Standard Ledger Inc."
) {
    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
        ) {
            Column {
                Column(modifier = Modifier.padding(32.dp).padding(bottom = 16.dp)) {
                    // Header
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 24.dp)) {
                        Box(modifier = Modifier.width(8.dp).height(32.dp).background(MaterialTheme.colorScheme.primary))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("CONFIRM APPROVAL", fontWeight = FontWeight.Black, fontSize = 24.sp, letterSpacing = 1.sp)
                    }

                    // Body
                    Text(
                        buildAnnotatedString {
                            append("Are you sure you want to share these fields with ")
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)) {
                                append(companyName)
                            }
                            append("? This action will use a ")
                            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontStyle = FontStyle.Italic)) {
                                append("disposable session key")
                            }
                            append(".")
                        },
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(bottom = 32.dp)
                    )

                    // Detail Block
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                            .padding(16.dp)
                            .padding(bottom = 32.dp)
                    ) {
                        Icon(Icons.Rounded.Info, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp).padding(top = 2.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("SESSION INTEGRITY", fontWeight = FontWeight.Black, fontSize = 10.sp, letterSpacing = 1.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("The key will expire automatically in 15 minutes or upon data delivery confirmation.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f), lineHeight = 16.sp)
                        }
                    }

                    // Action Buttons
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(
                            onClick = onConfirm,
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                                Text("CONFIRM APPROVAL", fontWeight = FontWeight.Black, fontSize = 14.sp, letterSpacing = 2.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = null, modifier = Modifier.size(20.dp))
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onCancel() },
                            contentAlignment = Alignment.Center
                        ) {
                            Text("CANCEL", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Black, fontSize = 14.sp, letterSpacing = 2.sp)
                        }
                    }
                }

                // Bottom Accent Bar
                Box(modifier = Modifier.fillMaxWidth().height(6.dp).background(MaterialTheme.colorScheme.primary))
            }
        }
    }
}
