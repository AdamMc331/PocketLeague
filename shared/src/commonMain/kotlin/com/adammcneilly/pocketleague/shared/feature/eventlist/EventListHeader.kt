package com.adammcneilly.pocketleague.shared.feature.eventlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adammcneilly.pocketleague.shared.models.EventFilter
import com.adammcneilly.pocketleague.shared.ui.components.FilterChip

// Color definitions from the palette
object EventsColors {
    val Background = Color(0xFF0F172A) // Slate 900
    val Surface = Color(0xFF0F172A) // Slate 900
    val SurfaceBorder = Color(0xFF334155) // Slate 700
    val Primary = Color(0xFF22D3EE) // Cyan 400
    val PrimaryDim = Color(0xFF67E8F9).copy(alpha = 0.7f) // Cyan 300 with opacity
    val OnPrimary = Color(0xFF020617) // Slate 950
    val TextPrimary = Color(0xFFE2E8F0) // Slate 200
    val TextSecondary = Color(0xFFCBD5E1) // Slate 300
    val ChipBackground = Color(0xFF1E293B) // Slate 800
}

@Composable
fun EventListHeader(
    selectedFilter: EventFilter,
    onFilterSelected: (EventFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = EventsColors.Background,
        shadowElevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            // Header Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = EventsColors.Surface,
                border = BorderStroke(1.dp, EventsColors.Primary.copy(alpha = 0.3f)),
                shadowElevation = 16.dp,
                tonalElevation = 0.dp,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    // Title and Filter Button Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        // Title Section
                        Column {
                            Text(
                                text = "Events",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = EventsColors.Primary,
                            )
                            Text(
                                text = "RLCS 2024-25",
                                fontSize = 12.sp,
                                color = EventsColors.PrimaryDim,
                            )
                        }

                        // Filter Icon Button
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    EventsColors.ChipBackground,
                                    RoundedCornerShape(8.dp),
                                )
                                .border(
                                    1.dp,
                                    EventsColors.SurfaceBorder,
                                    RoundedCornerShape(8.dp),
                                ),
                        ) {
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "Filter",
                                tint = EventsColors.Primary,
                                modifier = Modifier.size(16.dp),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Filter Chips Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        EventFilter.entries.forEach { filter ->
                            FilterChip(
                                label = filter.label,
                                selected = selectedFilter == filter,
                                onClick = { onFilterSelected(filter) },
                            )
                        }
                    }
                }
            }
        }
    }
}
