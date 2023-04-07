package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.placeholder.cardPlaceholder
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel

/**
 * Renders a [match] inside a card component. Likely to be used in a carousel of recent matches,
 * but is intentionally agnostic of where it could be used.
 */
@Composable
fun MatchCard(
    match : MatchDetailDisplayModel,
    onClick : (String) -> Unit,
    modifier : Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clickable(
                enabled = ! match.isPlaceholder,
                onClick = {
                    onClick.invoke(match.matchId)
                },
            )
            .semantics {
                isPlaceholder = match.isPlaceholder
            },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(16.dp),
        ) {
            EventName(match)

            RelativeTime(match)

            Spacer(
                modifier = Modifier.height(8.dp),
            )

            BlueTeamResult(match)

            OrangeTeamResult(match)
        }
    }
}

@Composable
private fun EventName(match : MatchDetailDisplayModel) {
    Text(
        text = match.eventName,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth()
            .cardPlaceholder(
                visible = match.isPlaceholder,
            ),
    )
}

@Composable
private fun RelativeTime(match : MatchDetailDisplayModel) {
    Text(
        text = match.relativeDateTime,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier
            .defaultMinSize(minWidth = 50.dp)
            .cardPlaceholder(
                visible = match.isPlaceholder,
            ),
    )
}

/**
 * NOTE: For showing the little star icon after the team name, we referenced
 * this StackOverflow answer. https://stackoverflow.com/a/67611627/3131147
 */
@Composable
private fun MatchTeamResultRow(
    teamResult : MatchTeamResultDisplayModel,
    isPlaceholder : Boolean,
    teamColor : String,
) {
    val fontWeight : FontWeight? = if (teamResult.winner) {
        FontWeight.Bold
    } else {
        null
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .cardPlaceholder(
                visible = isPlaceholder,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = teamResult.score.toString(),
            fontWeight = fontWeight,
            modifier = Modifier
                .testTag("${teamColor}_match_score"),
        )

        Text(
            text = teamResult.team.name,
            fontWeight = fontWeight,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .weight(1f, false)
                .padding(start = 16.dp, end = 4.dp)
                .testTag("${teamColor}_match_team_name"),
        )

        if (teamResult.winner){

            Icon(
                Icons.Default.EmojiEvents,
                contentDescription = null,
                modifier = Modifier
                    .size(with(LocalDensity.current) { LocalTextStyle.current.fontSize.toDp() }),
            )
        }
    }
}

@Composable
private fun OrangeTeamResult(match : MatchDetailDisplayModel) {
    MatchTeamResultRow(
        teamResult = match.orangeTeamResult,
        isPlaceholder = match.isPlaceholder,
        teamColor = "orange",
    )
}

@Composable
private fun BlueTeamResult(match : MatchDetailDisplayModel) {
    MatchTeamResultRow(
        teamResult = match.blueTeamResult,
        isPlaceholder = match.isPlaceholder,
        teamColor = "blue",
    )
}
