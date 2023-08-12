package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme

@Composable
internal fun RosterCardListItem(
    player: PlayerDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = PocketLeagueTheme.sizes.cardPadding,
                vertical = PocketLeagueTheme.sizes.cardPadding.div(2),
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.cardPadding),
    ) {
        FlagEmoji(player)

        Column {
            PlayerName(player)
            PlayerTag(player)
            PlayerRole(player)
        }
    }
}

@Composable
private fun PlayerRole(player: PlayerDisplayModel) {
    val role = player.role

    if (role != null) {
        Text(
            text = role,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun PlayerTag(player: PlayerDisplayModel) {
    Text(
        text = player.tag,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
private fun PlayerName(player: PlayerDisplayModel) {
    Text(
        text = player.name,
        style = MaterialTheme.typography.labelSmall,
    )
}

@Composable
private fun FlagEmoji(player: PlayerDisplayModel) {
    Text(
        text = player.countryFlagEmojiUnicode,
    )
}