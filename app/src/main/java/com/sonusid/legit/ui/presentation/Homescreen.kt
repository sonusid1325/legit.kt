package com.sonusid.legit.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sonusid.legit.ui.components.*
import com.sonusid.legit.ui.theme.LegitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    
    // State to track which sheet to show
    var activeSheet by remember { mutableStateOf<SheetType?>(null) }
    
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LegitTopBar(
                scrollBehavior = scrollBehavior,
                onNotificationClick = { activeSheet = SheetType.NOTIFICATIONS },
                onVerificationClick = { activeSheet = SheetType.VERIFICATIONS }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* TODO */ },
                icon = { Icon(Icons.Rounded.Add, contentDescription = "Verify") },
                text = { Text("Start Verification") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = MaterialTheme.shapes.large
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            item {
                WelcomeCard(name = "Firoj")
            }

            item {
                PrivacyBanner()
            }

            item {
                FeaturedDocsSection()
            }
            
            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    SectionHeader(title = "Verification Pipeline")
                    PipelineStepCard(
                        title = "KYC Check: Bank of India",
                        status = "PROOF SHARED",
                        timestamp = "2 mins ago",
                        icon = Icons.Rounded.CheckCircle,
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                    PipelineStepCard(
                        title = "Employment Verification",
                        status = "PROCESSING",
                        timestamp = "Analyzing locally...",
                        icon = Icons.Rounded.Refresh,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                    PipelineStepCard(
                        title = "Identity Hash",
                        status = "SECURED",
                        timestamp = "Yesterday",
                        icon = Icons.Rounded.Lock,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }

        // Bottom Sheet Logic
        activeSheet?.let { sheetType ->
            ModalBottomSheet(
                onDismissRequest = { activeSheet = null },
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                dragHandle = { BottomSheetDefaults.DragHandle() }
            ) {
                when (sheetType) {
                    SheetType.NOTIFICATIONS -> NotificationSheetContent()
                    SheetType.VERIFICATIONS -> VerificationSheetContent()
                }
            }
        }
    }
}

enum class SheetType {
    NOTIFICATIONS,
    VERIFICATIONS
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LegitTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES or android.content.res.Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun HomeScreenPreviewDark() {
    LegitTheme {
        HomeScreen()
    }
}
