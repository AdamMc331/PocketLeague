package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
    contentPadding: PaddingValues = PaddingValues(
        start = 16.dp,
        end = 16.dp,
    ),
) {
    val allSelected = (selectedRegions == Region.entries)

    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
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
        }

        items(Region.entries) { region ->
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
