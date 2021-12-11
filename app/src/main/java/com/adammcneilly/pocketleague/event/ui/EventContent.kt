package com.adammcneilly.pocketleague.event.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.swiss.ui.SwissRoundList

/**
 * The concrete UI content for the event screen based on the supplied [viewState].
 */
@Composable
fun EventContent(
    viewState: EventViewState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        viewState.swissStage?.rounds.orEmpty().forEach { swissRound ->
            SwissRoundList(swissRound = swissRound)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
