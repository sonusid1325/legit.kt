package com.sonusid.legit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.HowToVote
import androidx.compose.material.icons.rounded.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class FeaturedDoc(
    val name: String,
    val icon: ImageVector,
    val containerColor: Color,
    val contentColor: Color
)

@Composable
fun FeaturedDocsSection(onDocClick: (String) -> Unit) {
    val docs = listOf(
        FeaturedDoc(
            name = "Aadhar",
            icon = Icons.Rounded.Badge,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        FeaturedDoc(
            name = "PAN",
            icon = Icons.Rounded.CreditCard,
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        FeaturedDoc(
            name = "VoterID",
            icon = Icons.Rounded.HowToVote,
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ),
        FeaturedDoc(
            name = "ABC",
            icon = Icons.Rounded.School,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        FeaturedDoc(
            name = "Vault",
            icon = Icons.Rounded.GridView,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    )

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SectionHeader(title = "Featured Documents")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(docs) { doc ->
                DocCard(doc, onClick = { onDocClick(doc.name) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DocCard(doc: FeaturedDoc, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier.size(120.dp),
        shape = RoundedCornerShape(32.dp),
        color = doc.containerColor,
        contentColor = doc.contentColor
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = doc.contentColor.copy(alpha = 0.2f),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = doc.icon,
                        contentDescription = null,
                        tint = doc.contentColor,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
            
            Text(
                text = doc.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp,
                    lineHeight = 20.sp
                ),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
