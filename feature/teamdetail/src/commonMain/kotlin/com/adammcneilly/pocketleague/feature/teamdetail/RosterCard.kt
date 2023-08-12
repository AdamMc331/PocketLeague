package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel
import com.adammcneilly.pocketleague.shared.ui.components.ListItemDividerCard

/**
 * Shows a [ListItemDividerCard] with the supplied [roster].
 */
@Composable
internal fun RosterCard(
    roster: List<PlayerDisplayModel>,
    modifier: Modifier = Modifier,
) {
    ListItemDividerCard(
        items = roster,
        modifier = modifier,
    ) { player ->
        RosterCardListItem(player)
    }
}
