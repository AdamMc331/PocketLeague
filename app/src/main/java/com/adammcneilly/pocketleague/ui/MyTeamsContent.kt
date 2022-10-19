package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.myteams.MyTeamsViewState

/**
 * Render the supplied [viewState] to show the user's teams.
 */
@Composable
fun MyTeamsContent(
    viewState: MyTeamsViewState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Text(
            "Stubbed my teams screen",
        )
    }
}
