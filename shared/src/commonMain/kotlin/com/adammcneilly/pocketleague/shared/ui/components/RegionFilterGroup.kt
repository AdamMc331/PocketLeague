package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.models.Region

@Composable
fun RegionFilterGroup(
    selectedRegions: List<Region>,
    modifier: Modifier = Modifier,
) {
    val allSelected = (selectedRegions == Region.entries)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        FilterChip(
            selected = allSelected,
            onClick = {
            },
            label = {
                Text(
                    text = "All",
                )
            },
        )

        Region.entries.forEach { region ->
            FilterChip(
                selected = !allSelected && selectedRegions.contains(region),
                onClick = {
                },
                label = {
                    Text(
                        text = region.name,
                    )
                },
            )
        }
    }
}
