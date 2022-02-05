package com.adammcneilly.pocketleague.core.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme

private const val CARD_ASPECT_RATIO = 4.0F

/**
 * An instance of a [Material3Card] that presents a label explaining that we don't have
 * information we're trying to show in a section.
 */
@Composable
fun InformationUnavailableCard(
    modifier: Modifier = Modifier,
) {
    Material3Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(CARD_ASPECT_RATIO),
    ) {
        Box {
            Text(
                text = "This information is currently unavailable.",
                modifier = Modifier
                    .align(Alignment.Center),
            )
        }
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
private fun InformationUnavailableCardPreview() {
    PocketLeagueTheme {
        InformationUnavailableCard()
    }
}
