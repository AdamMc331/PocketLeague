package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        val detailDisplayModel = viewState.matchDetail

        if (detailDisplayModel != null) {
            MatchDetail(
                displayModel = detailDisplayModel,
                games = viewState.games.orEmpty(),
            )
        }

        if (viewState.showLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
            )
        }
    }
}
