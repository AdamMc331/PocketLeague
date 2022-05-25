package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.google.accompanist.placeholder.placeholder

/**
 * Displays a match between two teams inside a list item.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MatchListItem(
    displayModel: MatchDetailDisplayModel,
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
                text = displayModel.eventName,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .placeholder(
                        visible = displayModel.eventName.isBlank(),
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.inverseSurface,
                    )
            )

            Text(
                text = displayModel.relativeDate,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .defaultMinSize(minWidth = 50.dp)
                    .placeholder(
                        visible = displayModel.relativeDate.isBlank(),
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.inverseSurface,
                    )
            )

            Spacer(
                modifier = Modifier.height(8.dp),
            )

            MatchTeamResultRow(teamResult = displayModel.blueTeamResult)

            MatchTeamResultRow(teamResult = displayModel.orangeTeamResult)
        }
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
            modifier = Modifier
                .placeholder(
                    visible = teamResult.score.isBlank(),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.inverseSurface,
                )
        )

        Text(
            text = teamResult.getDisplayName(),
            fontWeight = fontWeight,
            modifier = Modifier
                .defaultMinSize(minWidth = 100.dp)
                .placeholder(
                    visible = teamResult.team.name.isBlank(),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.inverseSurface,
                ),
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

private fun MatchTeamResultDisplayModel.getInlineContent(): Map<String, InlineTextContent> {
    return if (this.winner) {
        mapOf(
            Pair(
                "inlineContent",
                InlineTextContent(
                    Placeholder(
                        width = 12.sp,
                        height = 12.sp,
                        placeholderVerticalAlign = androidx.compose.ui.text.PlaceholderVerticalAlign.AboveBaseline,
                    )
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = null,
                    )
                }
            )
        )
    } else {
        mapOf()
    }
}
