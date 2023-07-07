package com.adammcneilly.pocketleague.shared.ui.match

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.components.InlineIconText
import com.adammcneilly.pocketleague.shared.ui.placeholder.PlaceholderDefaults
import com.adammcneilly.pocketleague.shared.ui.placeholder.placeholderMaterial
import com.adammcneilly.pocketleague.shared.ui.utils.VerticalSpacer

/**
 * Renders a [match] inside a card component. Likely to be used in a carousel of recent matches,
 * but is intentionally agnostic of where it could be used.
 */
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
            ),
    ) {
        Column(
            modifier = Modifier
                .padding(PocketLeagueTheme.sizes.cardPadding),
        ) {
            EventName(match)

            RelativeTime(match)

            VerticalSpacer(PocketLeagueTheme.sizes.cardPadding)

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
            .placeholderMaterial(
                visible = match.isPlaceholder,
                color = PlaceholderDefaults.cardColor(),
            ),
    )
}

@Composable
private fun RelativeTime(match: MatchDetailDisplayModel) {
    Text(
        text = match.relativeDateTime,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier
            // Set for placeholder to show some portion of this component
            // Maybe there's a better way/we can configure this inside placeholder.
            .defaultMinSize(minWidth = 200.dp)
            .placeholderMaterial(
                visible = match.isPlaceholder,
                color = PlaceholderDefaults.cardColor(),
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
    teamColor: String,
) {
    val fontWeight: FontWeight? = if (teamResult.winner) {
        FontWeight.Bold
    } else {
        null
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.cardPadding),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = teamResult.score.toString(),
            fontWeight = fontWeight,
            modifier = Modifier
                .testTag("${teamColor}_match_score")
                .placeholderMaterial(
                    visible = teamResult.isPlaceholder,
                    color = PlaceholderDefaults.cardColor(),
                ),
        )

        InlineIconText(
            text = teamResult.team.name,
            icon = Icons.Default.EmojiEvents,
            showIcon = teamResult.winner,
            modifier = Modifier
                .weight(1F)
                .placeholderMaterial(
                    visible = teamResult.isPlaceholder,
                    color = PlaceholderDefaults.cardColor(),
                ),
        )
    }
}

@Composable
private fun OrangeTeamResult(match: MatchDetailDisplayModel) {
    MatchTeamResultRow(
        teamResult = match.orangeTeamResult,
        teamColor = "orange",
    )
}

@Composable
private fun BlueTeamResult(match: MatchDetailDisplayModel) {
    MatchTeamResultRow(
        teamResult = match.blueTeamResult,
        teamColor = "blue",
    )
}
