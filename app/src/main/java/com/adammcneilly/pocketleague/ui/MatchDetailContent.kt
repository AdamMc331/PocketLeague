package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.matchdetail.MatchDetailViewState

/**
 * The UI content of the match detail screen.
 */
@Composable
fun MatchDetailContent(
    viewState: MatchDetailViewState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(text = viewState.toString())
    }
}
