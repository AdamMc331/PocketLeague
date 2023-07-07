package com.adammcneilly.pocketleague.android.designsystem.myteams

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.adammcneilly.pocketleague.android.designsystem.utils.getForTheme
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.ui.placeholder.PlaceholderDefaults
import com.adammcneilly.pocketleague.shared.ui.placeholder.placeholderMaterial

/**
 * Renders a [displayModel] within our row of favorited teams
 * by the user.
 */
@Composable
fun FavoriteTeamRowItem(
    displayModel: TeamOverviewDisplayModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(8.dp),
        ) {
            val imageUrl = displayModel.imageUrl.getForTheme()

            val imageSize = 48.dp

            val hasImageLoaded = remember {
                mutableStateOf(false)
            }

            AsyncImage(
                model = imageUrl,
                contentDescription = "Team Image",
                modifier = Modifier
                    .size(imageSize)
                    .placeholderMaterial(
                        visible = displayModel.isPlaceholder || !hasImageLoaded.value,
                        color = PlaceholderDefaults.cardColor(),
                    ),
                onState = { state ->
                    hasImageLoaded.value = (state is AsyncImagePainter.State.Success)
                },
            )

            Text(
                text = displayModel.name,
                style = MaterialTheme.typography.headlineSmall,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .width(imageSize * 3)
                    .placeholderMaterial(
                        visible = displayModel.isPlaceholder,
                        color = PlaceholderDefaults.cardColor(),
                    ),
            )
        }
    }
}
