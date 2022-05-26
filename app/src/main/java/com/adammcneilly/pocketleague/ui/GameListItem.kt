package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TeamScore(displayModel = displayModel.blueTeamResult)

        Text(
            text = displayModel.map,
        )

        TeamScore(displayModel = displayModel.orangeTeamResult)
    }
}

@Composable
private fun TeamScore(
    displayModel: GameTeamResultDisplayModel,
) {
    val isWinner = displayModel.winner

    val fontWeight: FontWeight? = if (isWinner) {
        FontWeight.Bold
    } else {
        null
    }

    val inlineContentId = "inlineContent"

    val scoreText = buildAnnotatedString {
        append(displayModel.score)

        if (isWinner) {
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
