package com.adammcneilly.pocketleague.shared.ui.swiss

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.SwissTeamResultDisplayModel
import com.adammcneilly.pocketleague.shared.ui.components.CircleTeamLogo

/**
 * User friendly representation of a [SwissTeamResultDisplayModel].
 */
@Composable
fun SwissTeamResultListItem(
    displayModel: SwissTeamResultDisplayModel,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = {
            Text(
                text = displayModel.team.name,
            )
        },
        leadingContent = {
            CircleTeamLogo(
                displayModel = displayModel.team,
                modifier = Modifier
                    .size(48.dp),
            )
        },
        overlineContent = {
            Text(
                text = displayModel.overline,
            )
        },
        supportingContent = {
            Text(
                text = displayModel.subtitle,
            )
        },
        modifier = modifier,
    )
}
