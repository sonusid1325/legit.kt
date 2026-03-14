package com.sonusid.legit.ui.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sonusid.legit.ui.components.*
import com.sonusid.legit.ui.theme.LegitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProfileClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    
    // State to track which sheet to show for documents or other info
    var activeSheet by remember { mutableStateOf<SheetType?>(null) }
    
    // Entry animation state
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LegitTopBar(
                scrollBehavior = scrollBehavior,
                onNotificationClick = { activeSheet = SheetType.NOTIFICATIONS },
                onVerificationClick = { activeSheet = SheetType.VERIFICATIONS },
                onProfileClick = onProfileClick
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = visible,
                enter = scaleIn(animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)) + fadeIn()
            ) {
                ExtendedFloatingActionButton(
                    onClick = { /* TODO */ },
                    icon = { Icon(Icons.Rounded.Add, contentDescription = "Verify") },
                    text = { Text("Start Verification") },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    shape = MaterialTheme.shapes.large
                )
            }
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
                AnimatedEntryItem(visible = visible, index = 0) {
                    WelcomeCard(name = "Firoj Siddiquie")
                }
            }

            item {
                AnimatedEntryItem(visible = visible, index = 1) {
                    PrivacyBanner()
                }
            }

            item {
                AnimatedEntryItem(visible = visible, index = 2) {
                    FeaturedDocsSection(onDocClick = { docName ->
                        if (docName == "Aadhar") activeSheet = SheetType.DOC_AADHAR
                        if (docName == "PAN") activeSheet = SheetType.DOC_PAN
                    })
                }
            }
            
            item {
                AnimatedEntryItem(visible = visible, index = 3) {
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
                    SheetType.DOC_AADHAR -> DocumentViewSheet(docType = "Aadhar", onDismiss = { activeSheet = null })
                    SheetType.DOC_PAN -> DocumentViewSheet(docType = "PAN", onDismiss = { activeSheet = null })
                }
            }
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

enum class SheetType {
    NOTIFICATIONS,
    VERIFICATIONS,
    DOC_AADHAR,
    DOC_PAN
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LegitTheme {
        HomeScreen(onProfileClick = {})
    }
}
