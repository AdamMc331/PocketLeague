package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel

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
    val imageUrl = displayModel.imageUrl.lightThemeImageURL

    if (imageUrl != null) {
        RemoteImage(
            imageUrl = imageUrl,
            contentDescription = "Team Logo",
            modifier = modifier,
        )
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
    contentColor: Color,
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

        // Shout out Lemanja on YouTube for the text style idea:
        // https://www.youtube.com/watch?v=NBRNa1rtRSA&lc=UgxKzB3IGAXjIdH6VP54AaABAg
        Text(
            text = displayModel.name.firstOrNull()?.toString().orEmpty(),
            color = contentColor,
            fontSize = textSizeSp,
            lineHeight = textSizeSp,
            modifier = Modifier
                .align(Alignment.Center),
            // Need to find kmm version?
//            style = LocalTextStyle.current.merge(
//                TextStyle(
//                    platformStyle = PlatformTextStyle(
//                        includeFontPadding = false,
//                    ),
//                ),
//            ),
        )
    }
}
