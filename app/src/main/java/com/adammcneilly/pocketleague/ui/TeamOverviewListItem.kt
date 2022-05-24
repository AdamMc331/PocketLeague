package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme

/**
 * Renders the given [displayModel] for team overview information.
 */
@Composable
fun TeamOverviewListItem(
    displayModel: TeamOverviewDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val imageUrl = if (isSystemInDarkTheme()) {
            displayModel.darkThemeImageUrl
        } else {
            displayModel.lightThemeImageUrl
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Team Image",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
        )

        Text(
            text = displayModel.name,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
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
private fun TeamOverviewListItemPreview() {
    val team = TeamOverviewDisplayModel(
        teamId = "123",
        name = "Pittsburgh Knights",
    )

    PocketLeagueTheme {
        Surface {
            TeamOverviewListItem(displayModel = team)
        }
    }
}
