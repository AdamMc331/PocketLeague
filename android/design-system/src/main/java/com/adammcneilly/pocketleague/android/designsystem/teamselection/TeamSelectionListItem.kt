package com.adammcneilly.pocketleague.android.designsystem.teamselection

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel

@Composable
fun TeamSelectionListItem(
    team: TeamOverviewDisplayModel,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = team.name,
        )

        IconButton(
            onClick = { /*TODO*/ },
        ) {
            val icon = when (isFavorite) {
                true -> Icons.Filled.Star
                else -> Icons.Default.Star
            }

            val contentDescription = when (isFavorite) {
                true -> "Click To Remove Favorite"
                false -> "Click To Favorite"
            }

            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
        }
    }
}
