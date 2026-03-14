package com.sonusid.legit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class FeaturedDoc(
    val name: String,
    val icon: ImageVector,
    val color: androidx.compose.ui.graphics.Color
)

@Composable
fun FeaturedDocsSection() {
    val docs = listOf(
        FeaturedDoc("Aadhar", Icons.Rounded.Badge, MaterialTheme.colorScheme.primaryContainer),
        FeaturedDoc("PAN", Icons.Rounded.CreditCard, MaterialTheme.colorScheme.secondaryContainer),
        FeaturedDoc("VoterID", Icons.Rounded.HowToVote, MaterialTheme.colorScheme.tertiaryContainer),
        FeaturedDoc("ABC", Icons.Rounded.School, MaterialTheme.colorScheme.surfaceVariant),
        FeaturedDoc("All", Icons.Rounded.GridView, MaterialTheme.colorScheme.outlineVariant)
    )

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SectionHeader(title = "Featured Documents")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(docs) { doc ->
                DocCard(doc)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DocCard(doc: FeaturedDoc) {
    Surface(
        onClick = { /* TODO */ },
        modifier = Modifier
            .width(110.dp)
            .height(130.dp),
        shape = MaterialTheme.shapes.extraLarge,
        color = doc.color
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = doc.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = doc.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
