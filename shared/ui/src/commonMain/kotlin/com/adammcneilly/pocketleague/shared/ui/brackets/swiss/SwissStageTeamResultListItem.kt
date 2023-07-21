package com.adammcneilly.pocketleague.shared.ui.brackets.swiss

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.SwissStageTeamResultDisplayModel
import com.adammcneilly.pocketleague.shared.ui.components.CircleTeamLogo

/**
 * This is a list item that represents how a specific team performed
 * in a swiss stage.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwissStageTeamResultListItem(
    teamResult: SwissStageTeamResultDisplayModel,
    modifier: Modifier = Modifier,
) {
    ListItem(
        leadingContent = {
            Box {
                CircleTeamLogo(
                    displayModel = teamResult.team,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center),
                )
            }
        },
        headlineText = {
            Text(
                text = teamResult.team.name,
            )
        },
        supportingText = {
            Text(
                text = "${teamResult.matchRecord} | ${teamResult.gameRecord} | ${teamResult.gameDifferential}",
            )
        },
        overlineText = {
            Text(
                text = teamResult.overlineText,
            )
        },
        modifier = modifier,
    )
}
