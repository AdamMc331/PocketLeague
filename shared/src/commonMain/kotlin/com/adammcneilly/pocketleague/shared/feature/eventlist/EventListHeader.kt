package com.adammcneilly.pocketleague.shared.feature.eventlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.Filter1
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adammcneilly.pocketleague.shared.FilterChip
import com.adammcneilly.pocketleague.shared.FilterOption
import com.adammcneilly.pocketleague.shared.RLColors

@Composable
fun EventListHeader(
    filters: List<FilterOption>,
    selectedFilter: String,
    onFilterSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        RLColors.Cyan.copy(alpha = 0.2F),
                        RLColors.Blue.copy(alpha = 0.2F),
                    ),
                ),
            ),
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = RLColors.SlateBg.copy(alpha = 0.5F),
            border = BorderStroke(
                width = 1.dp,
                color = RLColors.Cyan.copy(alpha = 0.3F),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .statusBarsPadding(),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
                        Text(
                            text = "Events",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = RLColors.Cyan,
                        )
                        Text(
                            text = "RLCS 2024-25",
                            fontSize = 12.sp,
                            color = RLColors.CyanLight.copy(alpha = 0.7f),
                        )
                    }

                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                RLColors.SlateBg.copy(alpha = 0.5f),
                                RoundedCornerShape(8.dp),
                            )
                            .border(1.dp, RLColors.SlateLight, RoundedCornerShape(8.dp)),
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter",
                            tint = RLColors.Cyan,
                            modifier = Modifier.size(16.dp),
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(16.dp),
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    filters.forEach { filter ->
                        FilterChip(
                            label = filter.label,
                            selected = selectedFilter == filter.id,
                            onClick = { onFilterSelected(filter.id) },
                        )
                    }
                }
            }
        }
    }
}
