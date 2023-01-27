package com.adammcneilly.pocketleague.android.designsystem.team

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adammcneilly.pocketleague.android.designsystem.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.android.designsystem.utils.getForTheme
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL

/**
 * This will render a circular image with the logo for the given [displayModel].
 *
 * If no logo is available, we'll fall back to just rendering the first letter of the team inside a
 * small circle.
 */
@Composable
fun CircleTeamLogo(
    displayModel: TeamOverviewDisplayModel,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor),
) {
    val imageUrl = displayModel.imageUrl.getForTheme()

    if (imageUrl != null) {
        RemoteImage(imageUrl, modifier)
    } else {
        TeamLetterLogo(modifier, backgroundColor, displayModel, contentColor)
    }
}

/**
 * At the moment, this works but we don't see that the text is fully centered in some situations.
 * We're not fully sure why, may need to tweak how bottom line padding is set, or something
 * similar.
 */
@Composable
@Suppress("MagicNumber")
private fun TeamLetterLogo(
    modifier: Modifier,
    backgroundColor: Color,
    displayModel: TeamOverviewDisplayModel,
    contentColor: Color
) {
    BoxWithConstraints(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = CircleShape,
            )
            .border(
                width = 1.dp,
                color = contentColor,
                shape = CircleShape,
            ),
    ) {
        val textSizeSp = with(LocalDensity.current) {
            val sizeInPx = (maxHeight * 0.50F).toPx()
            sizeInPx.toSp()
        }

        Text(
            text = displayModel.name.firstOrNull()?.toString().orEmpty(),
            color = contentColor,
            fontSize = textSizeSp,
            lineHeight = textSizeSp,
            modifier = Modifier
                .align(Alignment.Center),
        )
    }
}

@Composable
private fun RemoteImage(imageUrl: String?, modifier: Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Team Image",
        modifier = modifier,
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun CircleTeamLogoDefaultPreview() {
    val displayModel = TeamOverviewDisplayModel(
        imageUrl = ThemedImageURL(),
        teamId = "",
        name = "Knights",
        isPlaceholder = false,
        isFavorite = false,
    )

    PocketLeagueTheme {
        CircleTeamLogo(
            displayModel = displayModel,
            modifier = Modifier
                .size(48.dp),
        )
    }
}
