package com.adammcneilly.pocketleague.shared.ui.swiss

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.SwissTeamResultDisplayModel
import com.adammcneilly.pocketleague.shared.ui.components.CircleTeamLogo
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme

/**
 * User friendly representation of a [SwissTeamResultDisplayModel].
 */
@Composable
fun SwissTeamResultListItem(
    displayModel: SwissTeamResultDisplayModel,
    modifier: Modifier = Modifier,
) {
    val colorToUse = if (displayModel.overline == "Qualified") {
        Color.Green
    } else {
        Color.Red
    }

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min),
    ) {
        Box(
            modifier = Modifier
                .background(color = colorToUse)
                .width(4.dp)
                .fillMaxHeight(),
        )

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.listItemSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircleTeamLogo(
                displayModel = displayModel.team,
                modifier = Modifier
                    .size(36.dp),
            )

            Column(
                modifier = Modifier
                    .weight(1F),
            ) {
//            Text(
//                text = displayModel.overline,
//                style = MaterialTheme.typography.labelSmall,
//                fontWeight = FontWeight.Bold,
//            )

                Text(
                    text = displayModel.team.name,
                    style = MaterialTheme.typography.bodyLarge,
                )

                Text(
                    text = displayModel.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
