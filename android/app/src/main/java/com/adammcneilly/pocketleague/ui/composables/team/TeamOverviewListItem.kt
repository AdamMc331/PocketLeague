package com.adammcneilly.pocketleague.ui.composables.team

import androidx.compose.foundation.layout.Arrangement
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
import com.adammcneilly.pocketleague.android.designsystem.placeholder.cardPlaceholder
import com.adammcneilly.pocketleague.android.designsystem.team.CircleTeamLogo
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel

/**
 * Renders the given [displayModel] for team overview information.
 */
@Composable
fun TeamOverviewListItem(
    displayModel: TeamOverviewDisplayModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CircleTeamLogo(
            displayModel = displayModel,
            modifier = Modifier
                .size(48.dp)
                .cardPlaceholder(
                    visible = displayModel.isPlaceholder

                )
        )

        Text(
            text = displayModel.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .cardPlaceholder(
                    visible = displayModel.isPlaceholder
                )
                .weight(1F)
        )
    }
}
