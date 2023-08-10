package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme

@Composable
internal fun RosterCardListItem(
    player: PlayerDisplayModel,
    modifier: Modifier = Modifier,
) {
    Text(
        text = player.tag,
        modifier = modifier
            .padding(PocketLeagueTheme.sizes.cardPadding),
    )
}
