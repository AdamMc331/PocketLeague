package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RosterCardListItem(
    player: PlayerDisplayModel,
    modifier: Modifier = Modifier,
) {
    val playerText = listOfNotNull(
        player.tag,
        player.role,
    ).joinToString(" – ")

    ListItem(
        modifier = modifier,
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        headlineText = {
            Text(
                text = playerText,
            )
        },
    )
}
