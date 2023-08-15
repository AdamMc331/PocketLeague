package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.placeholder.PlaceholderDefaults
import com.adammcneilly.pocketleague.shared.ui.placeholder.placeholderMaterial

private val FLAG_EMOJI_SIZE = 24.dp

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
            PlayerTag(player)
            PlayerName(player)
        }
    }
}

@Composable
private fun PlayerTag(player: PlayerDisplayModel) {
    Text(
        text = listOfNotNull(
            player.tag,
            player.role,
        ).joinToString(" "),
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .placeholderMaterial(
                visible = player.isPlaceholder,
                color = PlaceholderDefaults.cardColor(),
            ),
    )
}

@Composable
@Suppress("MagicNumber")
private fun PlayerName(player: PlayerDisplayModel) {
    Text(
        text = player.name,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier
            .fillMaxWidth(0.5F)
            .placeholderMaterial(
                visible = player.isPlaceholder,
                color = PlaceholderDefaults.cardColor(),
            ),
    )
}

@Composable
private fun FlagEmoji(player: PlayerDisplayModel) {
    Box(
        modifier = Modifier
            .size(FLAG_EMOJI_SIZE)
            .placeholderMaterial(
                visible = player.isPlaceholder,
                color = PlaceholderDefaults.cardColor(),
            ),
    ) {
        Text(
            text = player.countryFlagEmojiUnicode,
            modifier = Modifier
                .align(Alignment.Center),
        )
    }
}
