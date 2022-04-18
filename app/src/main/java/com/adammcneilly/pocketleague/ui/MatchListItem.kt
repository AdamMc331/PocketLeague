package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.models.Match
import com.adammcneilly.pocketleague.shared.models.MatchTeamResult
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

/**
 * Displays a match between two teams inside a list item.
 */
@Composable
fun MatchListItem(
    match: Match,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = match.event.name,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = match.date?.getRelativeTimestamp().orEmpty(),
                style = MaterialTheme.typography.caption,
            )

            Spacer(
                modifier = Modifier.height(16.dp),
            )

            MatchTeamResultRow(teamResult = match.blueTeam)

            MatchTeamResultRow(teamResult = match.orangeTeam)
        }
    }
}

@Composable
private fun MatchTeamResultRow(
    teamResult: MatchTeamResult,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = teamResult.score.toString(),
            color = if (teamResult.winner) {
                Color.Green
            } else {
                Color.Red
            },
        )

        Text(
            text = teamResult.team.name,
            fontWeight = if (teamResult.winner) {
                FontWeight.Bold
            } else {
                null
            },
        )
    }
}

private const val HOURS_IN_DAY = 24

private fun LocalDateTime.getRelativeTimestamp(): String {
    val now = Clock.System.now()
    val matchInstant = this.toInstant(TimeZone.currentSystemDefault())

    val duration = now.minus(matchInstant)

    return when {
        duration.inWholeHours < HOURS_IN_DAY -> {
            "${duration.inWholeHours}h ago"
        }
        else -> {
            "${duration.inWholeDays}d ago"
        }
    }
}
