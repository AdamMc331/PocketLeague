@file:Suppress("MatchingDeclarationName")

package com.adammcneilly.pocketleague.android.designsystem.teamselection

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.adammcneilly.pocketleague.android.designsystem.placeholder.cardPlaceholder
import com.adammcneilly.pocketleague.android.designsystem.utils.getForTheme
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel

/**
 * This defines all the possible click actions that we want to expose
 * from a [TeamSelectionListItem].
 */
interface TeamSelectionListItemClickListener {

    /**
     * Notifies a receive that the [isFavorite] status for a given [teamId] is changed.
     */
    fun onFavoriteChanged(
        teamId: String,
        isFavorite: Boolean,
    )
}

/**
 * Renders a list item to be used inside the team selection screen, allowing the user to change
 * whether or not this [team] is one of their favorites.
 *
 */
@Composable
fun TeamSelectionListItem(
    team: TeamOverviewDisplayModel,
    clickListener: TeamSelectionListItemClickListener,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(25.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val imageUrl = team.imageUrl.getForTheme()

        val imageSize = 48.dp

        val hasImageLoaded = remember {
            mutableStateOf(false)
        }
        
        AsyncImage(
            model = imageUrl,
            contentDescription = "Team Image",
            modifier = Modifier
                .size(imageSize)
                .cardPlaceholder(
                    visible = team.isPlaceholder || !hasImageLoaded.value,
                ),
            onState = { state ->
                hasImageLoaded.value = (state is AsyncImagePainter.State.Success)
            },
        )

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = team.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .weight(1F),
        )

        IconButton(
            onClick = {
                clickListener.onFavoriteChanged(
                    teamId = team.teamId,
                    isFavorite = !team.isFavorite,
                )
            },
        ) {
            val icon = when {
                team.isFavorite -> Icons.Default.Star
                else -> Icons.Default.StarBorder
            }

            val contentDescription = when {
                team.isFavorite -> "Click To Remove Favorite"
                else -> "Click To Favorite"
            }

            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
        }
    }
}
