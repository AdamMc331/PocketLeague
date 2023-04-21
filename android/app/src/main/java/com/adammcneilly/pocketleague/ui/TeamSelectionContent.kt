package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.teamselection.TeamSelectionListItem
import com.adammcneilly.pocketleague.android.designsystem.teamselection.TeamSelectionListItemClickListener
import com.adammcneilly.pocketleague.shared.screens.teamselection.TeamSelectionViewState

/**
 * This renders the given [viewState] to show a selection of teams that the user can favorite.
 */
@Composable
fun TeamSelectionContent(
    viewState: TeamSelectionViewState,
    listItemClickListener: TeamSelectionListItemClickListener,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        item {
            RegionSelectionDropdown(
                viewState = viewState,
                clickListener = listItemClickListener,
            )
        }

        val teams = viewState.filteredTeams

        itemsIndexed(teams) { index, team ->
            TeamSelectionListItem(
                team = team,
                clickListener = listItemClickListener,
            )

            if (index != teams.lastIndex) {
                Divider()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun RegionSelectionDropdown(
    viewState: TeamSelectionViewState,
    clickListener: TeamSelectionListItemClickListener,
) {
    // This code is duplicated, we really should have a shared enum for team regions.
    val regions = listOf(
        "All Regions",
        "Europe",
        "North America",
        "Oceania",
        "South America",
        "Middle East and North Africa",
        "Asia-Pacific",
    )

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        OutlinedTextField(
            value = viewState.selectedRegionName,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            regions.forEach { regionName ->
                DropdownMenuItem(
                    text = {
                        Text(text = regionName)
                    },
                    onClick = {
                        expanded = false
                        clickListener.onRegionChanged(regionName)
                    },
                )
            }
        }
    }
}
