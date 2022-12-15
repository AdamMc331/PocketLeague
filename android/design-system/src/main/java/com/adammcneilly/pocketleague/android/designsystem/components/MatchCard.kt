package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
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
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MatchCard(
    match: MatchDetailDisplayModel,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clickable(
                enabled = !match.isPlaceholder,
                onClick = {
                    onClick.invoke(match.matchId)
                },
            )
            .semantics {
                isPlaceholder = match.isPlaceholder
                testTag = "MATCH_CARD"
                testTagsAsResourceId = true
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
private fun EventName(match: MatchDetailDisplayModel) {
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
private fun RelativeTime(match: MatchDetailDisplayModel) {
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
    teamResult: MatchTeamResultDisplayModel,
    isPlaceholder: Boolean,
    teamColor: String,
) {
    val fontWeight: FontWeight? = if (teamResult.winner) {
        FontWeight.Bold
    } else {
        null
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .cardPlaceholder(
                visible = isPlaceholder,
            ),
    ) {
        Text(
            text = teamResult.score.toString(),
            fontWeight = fontWeight,
            modifier = Modifier
                .testTag("${teamColor}_match_score"),
        )

        Text(
            text = teamResult.getDisplayName(),
            fontWeight = fontWeight,
            inlineContent = teamResult.getInlineContent(),
            modifier = Modifier
                .testTag("${teamColor}_match_team_name"),
        )
    }
}

private fun MatchTeamResultDisplayModel.getDisplayName(): AnnotatedString {
    return buildAnnotatedString {
        append(team.name)

        if (winner) {
            append(" ")
            appendInlineContent("inlineContent", "[winner]")
        }
    }
}

@Composable
private fun MatchTeamResultDisplayModel.getInlineContent(): Map<String, InlineTextContent> {
    return if (this.winner) {
        mapOf(
            Pair(
                "inlineContent",
                InlineTextContent(
                    Placeholder(
                        width = LocalTextStyle.current.fontSize,
                        height = LocalTextStyle.current.fontSize,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
                    )
                ) {
                    Icon(
                        Icons.Default.EmojiEvents,
                        contentDescription = null,
                    )
                }
            )
        )
    } else {
        mapOf()
    }
}

@Composable
private fun OrangeTeamResult(match: MatchDetailDisplayModel) {
    MatchTeamResultRow(
        teamResult = match.orangeTeamResult,
        isPlaceholder = match.isPlaceholder,
        teamColor = "orange",
    )
}

@Composable
private fun BlueTeamResult(match: MatchDetailDisplayModel) {
    MatchTeamResultRow(
        teamResult = match.blueTeamResult,
        isPlaceholder = match.isPlaceholder,
        teamColor = "blue",
    )
}
