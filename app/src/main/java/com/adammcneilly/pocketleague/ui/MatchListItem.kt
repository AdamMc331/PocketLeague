package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.models.Match
import com.adammcneilly.pocketleague.shared.models.MatchTeamResult
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.placeholder
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

/**
 * Displays a match between two teams inside a list item.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MatchListItem(
    match: Match,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = match.event.name,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .placeholder(
                        visible = match.event.name.isBlank(),
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.inverseSurface,
                    )
            )

            Text(
                text = match.date?.getRelativeTimestamp().orEmpty(),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .defaultMinSize(minWidth = 50.dp)
                    .placeholder(
                        visible = match.date == null,
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.inverseSurface,
                    )
            )

            Spacer(
                modifier = Modifier.height(8.dp),
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
    val fontWeight: FontWeight? = if (teamResult.winner) {
        FontWeight.Bold
    } else {
        null
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = teamResult.score.toString(),
            fontWeight = fontWeight,
            modifier = Modifier
                .placeholder(
                    visible = teamResult.score == -1,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.inverseSurface,
                )
        )

        Text(
            text = teamResult.team.name,
            fontWeight = fontWeight,
            modifier = Modifier
                .defaultMinSize(minWidth = 100.dp)
                .placeholder(
                    visible = teamResult.team.name.isBlank(),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.inverseSurface,
                )
        )

        if (teamResult.winner) {
            Icon(
                Icons.Filled.Star,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
        }
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
