package com.adammcneilly.pocketleague.event.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.swiss.ui.SwissRoundList

@Composable
fun EventContent(
    viewState: EventViewState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
    ) {
        items(viewState.swissStage?.rounds.orEmpty()) { swissRound ->
            SwissRoundList(swissRound = swissRound)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
