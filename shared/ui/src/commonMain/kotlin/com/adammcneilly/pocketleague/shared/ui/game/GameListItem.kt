package com.adammcneilly.pocketleague.shared.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.GameTeamResultDisplayModel
import com.adammcneilly.pocketleague.shared.ui.placeholder.PlaceholderDefaults
import com.adammcneilly.pocketleague.shared.ui.placeholder.placeholderMaterial
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme

private const val SCORE_TEXT_WEIGHT = 1F
private const val MAP_TEXT_WEIGHT = 4F

/**
 * Renders the [displayModel] to show information about a game between
 * two teams.
 */
@Composable
fun GameListItem(
    displayModel: GameDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(PocketLeagueTheme.sizes.cardPadding)
            .placeholderMaterial(
                visible = displayModel.isPlaceholder,
                color = PlaceholderDefaults.cardColor(),
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TeamScore(
            displayModel = displayModel.blueTeamResult,
            showIconFirst = false,
            textAlign = TextAlign.Start,
            weight = SCORE_TEXT_WEIGHT,
        )

        Column(
            modifier = Modifier
                .weight(MAP_TEXT_WEIGHT),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = displayModel.map,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            val otLabel = displayModel.otLabel

            if (otLabel != null) {
                Text(
                    text = otLabel,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        TeamScore(
            displayModel = displayModel.orangeTeamResult,
            showIconFirst = true,
            textAlign = TextAlign.End,
            weight = SCORE_TEXT_WEIGHT,
        )
    }
}

/**
 * This renders a team's score within a game, as well as an icon to signify
 * if they are the winning team.
 *
 * If the [showIconFirst] property is true, we render the icon in front of the score,
 * otherwise behind it.
 */
@Composable
private fun RowScope.TeamScore(
    displayModel: GameTeamResultDisplayModel,
    showIconFirst: Boolean,
    textAlign: TextAlign,
    weight: Float,
    modifier: Modifier = Modifier,
) {
    val isWinner = displayModel.winner

    val fontWeight: FontWeight? = if (isWinner) {
        FontWeight.Bold
    } else {
        null
    }

    val inlineContentId = "inlineContent"

    val scoreText = buildAnnotatedString {
        if (isWinner && showIconFirst) {
            appendInlineContent(inlineContentId, "[trophy]")

            append(" ")
        }

        append(displayModel.goals.toString())

        if (isWinner && !showIconFirst) {
            append(" ")

            appendInlineContent(inlineContentId, "[trophy]")
        }
    }

    val inlineContent: Map<String, InlineTextContent> = if (isWinner) {
        mapOf(
            Pair(
                inlineContentId,
                InlineTextContent(
                    Placeholder(
                        width = LocalTextStyle.current.fontSize,
                        height = LocalTextStyle.current.fontSize,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
                    ),
                ) {
                    Icon(
                        Icons.Default.EmojiEvents,
                        contentDescription = null,
                    )
                },
            ),
        )
    } else {
        mapOf()
    }

    Text(
        text = scoreText,
        fontWeight = fontWeight,
        inlineContent = inlineContent,
        textAlign = textAlign,
        modifier = modifier
            .weight(weight),
    )
}
