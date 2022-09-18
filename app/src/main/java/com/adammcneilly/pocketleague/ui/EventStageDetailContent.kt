package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.screens.eventstagedetail.EventStageDetailViewState
import com.adammcneilly.pocketleague.ui.composables.match.MatchListItem

/**
 * Renders the [viewState] for the event stage detail screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventStageDetailContent(
    viewState: EventStageDetailViewState,
    onMatchClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            viewState.matchesByDate.forEach { (date, matchList) ->
                item {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }

                item {
                    Card {
                        matchList.forEachIndexed { index, match ->
                            MatchListItem(
                                displayModel = match,
                                modifier = Modifier
                                    .clickable {
                                        onMatchClicked.invoke(match.matchId)
                                    }
                            )

                            if (index != matchList.lastIndex) {
                                Divider()
                            }
                        }
                    }
                }
            }
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
