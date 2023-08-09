package com.adammcneilly.pocketleague.feature.eventdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsBlue
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsOrange
import com.adammcneilly.pocketleague.shared.ui.components.RemoteImage
import com.adammcneilly.pocketleague.shared.ui.placeholder.PlaceholderDefaults
import com.adammcneilly.pocketleague.shared.ui.placeholder.placeholderMaterial
import com.adammcneilly.pocketleague.shared.ui.utils.conditional

private val cardShape = CutCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomEnd = 8.dp,
    bottomStart = 8.dp,
)

val teamLogoSize = 30.dp

/**
 * Renders a [match] list item to show inside a list of matches for a given
 * event stage.
 */
@Composable
internal fun MatchListItem(
    match: MatchDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = cardShape,
    ) {
        TeamResultRow(match)

        WinnerHighlightRow(match)
    }
}

@Composable
private fun WinnerHighlightRow(
    match: MatchDetailDisplayModel,
) {
    Row {
        WinnerHighlight(
            teamResult = match.blueTeamResult,
            color = rlcsBlue,
            modifier = Modifier
                .weight(1F),
        )

        WinnerHighlight(
            teamResult = match.orangeTeamResult,
            color = rlcsOrange,
            modifier = Modifier
                .weight(1F),
        )
    }
}

@Composable
private fun WinnerHighlight(
    teamResult: MatchTeamResultDisplayModel,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(4.dp)
            .conditional(teamResult.winner) {
                background(color = color)
            },
    )
}

@Composable
private fun TeamResultRow(
    match: MatchDetailDisplayModel,
) {
    Row(
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TeamLogo(match.blueTeamResult.team)

        TeamName(
            team = match.blueTeamResult.team,
            modifier = Modifier
                .weight(1F),
        )

        ScoreLabel(
            match = match,
            modifier = Modifier
                .weight(1F),
        )

        TeamName(
            team = match.orangeTeamResult.team,
            modifier = Modifier
                .weight(1F),
        )

        TeamLogo(match.orangeTeamResult.team)
    }
}

@Composable
private fun ScoreLabel(
    match: MatchDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    val scoreText = if (match.isPlaceholder) {
        ""
    } else {
        "${match.blueTeamResult.score}-${match.orangeTeamResult.score}"
    }

    Text(
        text = scoreText,
        textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = modifier,
    )
}

@Composable
private fun TeamName(
    team: TeamOverviewDisplayModel,
    modifier: Modifier = Modifier,
) {
    Text(
        text = team.name,
        textAlign = TextAlign.Center,
        maxLines = 1,
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier
            .padding(
                horizontal = PocketLeagueTheme.sizes.textSpacing,
            )
            .placeholderMaterial(
                visible = team.isPlaceholder,
                color = PlaceholderDefaults.cardColor(),
            ),
    )
}

@Composable
private fun TeamLogo(
    team: TeamOverviewDisplayModel,
    modifier: Modifier = Modifier,
) {
    RemoteImage(
        imageUrl = team.imageUrl.lightThemeImageURL.orEmpty(),
        contentDescription = null,
        modifier = modifier
            .size(teamLogoSize)
            .placeholderMaterial(
                visible = team.isPlaceholder,
                color = PlaceholderDefaults.cardColor(),
            ),
    )
}
