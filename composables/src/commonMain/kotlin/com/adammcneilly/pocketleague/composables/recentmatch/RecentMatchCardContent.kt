package com.adammcneilly.pocketleague.composables.recentmatch

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
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel

/**
 * Displays a match between two teams inside a list item.
 *
 * This is intended ot be used inside a card, but right now KMM Compose Material 3 does
 * not have a card component, so this can be used from that in Android.
 */
@Composable
fun RecentMatchCardContent(
    match: MatchDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = match.eventName,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Text(
            // TODO: Replace with relative date
            text = match.localDate,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .defaultMinSize(minWidth = 50.dp),
        )

        Spacer(
            modifier = Modifier.height(8.dp),
        )

        MatchTeamResultRow(teamResult = match.blueTeamResult)

        MatchTeamResultRow(teamResult = match.orangeTeamResult)
    }
}

/**
 * NOTE: For showing the little star icon after the team name, we referenced
 * this StackOverflow answer. https://stackoverflow.com/a/67611627/3131147
 */
@Composable
private fun MatchTeamResultRow(
    teamResult: MatchTeamResultDisplayModel,
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
            text = teamResult.score,
            fontWeight = fontWeight,
            modifier = Modifier,
        )

        Text(
            text = teamResult.getDisplayName(),
            fontWeight = fontWeight,
            modifier = Modifier
                .defaultMinSize(minWidth = 100.dp),
            inlineContent = teamResult.getInlineContent(),
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
