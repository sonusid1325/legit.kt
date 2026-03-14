package com.sonusid.legit.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class VerificationRequest(
    val id: Int,
    val requester: String,
    val documentType: String,
    val time: String,
    val icon: ImageVector = Icons.Rounded.Description
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationSheetContent() {
    var requests by remember {
        mutableStateOf(
            listOf(
                VerificationRequest(1, "Bank of India", "Aadhar XML", "10m ago"),
                VerificationRequest(2, "TechCorp LLC", "Degree Certificate", "1h ago"),
                VerificationRequest(3, "Landlord Services", "PAN Verification", "5h ago")
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        Text(
            text = "Pending Verifications",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )

        Text(
            text = "Swipe Right to Approve • Swipe Left to Decline",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 24.dp).padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(requests, key = { it.id }) { request ->
                SwipeableVerificationItem(
                    request = request,
                    onDismiss = { direction ->
                        requests = requests.filter { it.id != request.id }
                        // Handle approval or rejection logic here
                    }
                )
            }
        }
        
        if (requests.isEmpty()) {
            Box(Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                Text("All caught up!", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableVerificationItem(
    request: VerificationRequest,
    onDismiss: (SwipeToDismissBoxValue) -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.StartToEnd || it == SwipeToDismissBoxValue.EndToStart) {
                onDismiss(it)
                true
            } else false
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.Settled -> Color.Transparent
                    SwipeToDismissBoxValue.StartToEnd -> Color(0xFF4CAF50) // Approve (Green)
                    SwipeToDismissBoxValue.EndToStart -> Color(0xFFF44336) // Decline (Red)
                }, label = "color"
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(color, MaterialTheme.shapes.extraLarge)
                    .padding(horizontal = 20.dp),
                contentAlignment = if (dismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) 
                    Alignment.CenterStart else Alignment.CenterEnd
            ) {
                if (dismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) {
                    Icon(Icons.Rounded.Check, "Approve", tint = Color.White)
                } else if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) {
                    Icon(Icons.Rounded.Close, "Decline", tint = Color.White)
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.surfaceContainerHigh
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(request.icon, null, tint = MaterialTheme.colorScheme.primary)
                }
                Spacer(Modifier.width(16.dp))
                Column(Modifier.weight(1f)) {
                    Text(request.requester, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(request.documentType, style = MaterialTheme.typography.bodyMedium)
                }
                Text(request.time, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
