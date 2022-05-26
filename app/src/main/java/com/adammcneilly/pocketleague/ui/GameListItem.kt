package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.GameTeamResultDisplayModel
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme

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
            .padding(16.dp),
    ) {
        TeamScore(
            displayModel = displayModel.blueTeamResult,
            showIconFirst = false,
            textAlign = TextAlign.Start,
            weight = 1F,
        )

        Text(
            text = displayModel.map,
            modifier = Modifier
                .weight(4F),
            textAlign = TextAlign.Center,
        )

        TeamScore(
            displayModel = displayModel.orangeTeamResult,
            showIconFirst = true,
            textAlign = TextAlign.End,
            weight = 1F,
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

        append(displayModel.score)

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

    Text(
        text = scoreText,
        fontWeight = fontWeight,
        inlineContent = inlineContent,
        textAlign = textAlign,
        modifier = Modifier
            .weight(weight),
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun GameListItemPreview() {
    val game = GameDetailDisplayModel(
        orangeTeamResult = GameTeamResultDisplayModel(
            score = "1",
            winner = true,
        ),
        blueTeamResult = GameTeamResultDisplayModel(
            score = "0",
        ),
        map = "DFH Stadium",
    )

    PocketLeagueTheme {
        Surface {
            GameListItem(displayModel = game)
        }
    }
}
