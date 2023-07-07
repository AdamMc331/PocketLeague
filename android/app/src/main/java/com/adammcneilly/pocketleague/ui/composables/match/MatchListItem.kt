package com.adammcneilly.pocketleague.ui.composables.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.components.InlineIconText
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.shared.ui.placeholder.PlaceholderDefaults
import com.adammcneilly.pocketleague.shared.ui.placeholder.placeholderMaterial

/**
 * Renders a [displayModel] inside of an individual list item along side other matches
 * in a vertical scrolling manner.
 */
@Composable
fun MatchListItem(
    displayModel: MatchDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = displayModel.localTime,
            modifier = Modifier
                .placeholderMaterial(
                    visible = displayModel.isPlaceholder,
                    color = PlaceholderDefaults.cardColor(),
                ),
        )

        Column(
            modifier = Modifier
                .weight(1F),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MatchTeamResultRow(displayModel = displayModel.blueTeamResult)

            MatchTeamResultRow(displayModel = displayModel.orangeTeamResult)
        }
    }
}

@Composable
private fun MatchTeamResultRow(
    displayModel: MatchTeamResultDisplayModel,
) {
    val fontWeight: FontWeight? = if (displayModel.winner) {
        FontWeight.Bold
    } else {
        null
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = displayModel.score.toString(),
            fontWeight = fontWeight,
            modifier = Modifier
                .placeholderMaterial(
                    visible = displayModel.isPlaceholder,
                    color = PlaceholderDefaults.cardColor(),
                ),
        )

        InlineIconText(
            text = displayModel.team.name,
            icon = Icons.Default.EmojiEvents,
            showIcon = displayModel.winner,
            modifier = Modifier
                .weight(1F)
                .placeholderMaterial(
                    visible = displayModel.isPlaceholder,
                    color = PlaceholderDefaults.cardColor(),
                ),
        )
    }
}
