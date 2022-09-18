package com.adammcneilly.pocketleague.composables.team

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.composables.placeholder.placeholderMaterial
import com.adammcneilly.pocketleague.composables.utils.getForTheme
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import io.ktor.http.Url

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
        val imageUrl = displayModel.imageUrl.getForTheme().orEmpty()

        val imageSize = 48.dp

        if (imageUrl.isNotEmpty()) {
            KamelImage(
                resource = lazyPainterResource(
                    data = Url(imageUrl),
                ),
                contentDescription = "Team Image",
                modifier = Modifier
                    .size(imageSize),
                crossfade = true,
                onLoading = {
                    Box(
                        modifier = Modifier
                            .size(imageSize)
                            .placeholderMaterial(
                                visible = true,
                            ),
                    )
                },
            )
        }

        Text(
            text = displayModel.name,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}
