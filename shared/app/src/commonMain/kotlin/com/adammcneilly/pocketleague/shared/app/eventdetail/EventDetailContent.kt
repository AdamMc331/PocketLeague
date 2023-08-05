package com.adammcneilly.pocketleague.shared.app.eventdetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.components.RemoteImage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * UI representation for detailed info about an event.
 */
@Composable
fun EventDetailContent(
    state: EventDetailScreen.State,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            vertical = PocketLeagueTheme.sizes.screenPadding,
        ),
        verticalArrangement = Arrangement.spacedBy(
            PocketLeagueTheme.sizes.listItemSpacing,
        ),
    ) {
        // Do we want to pass in the selected stage?
        // Maybe not, since there's no situation where we dynamically link to it,
        // though maybe if we ever wanted to support deep linking.
        horizontalStageSection(state)

        items(state.matchesForSelectedStage) { match ->
            MatchListItem(match)
        }
    }
}

@Composable
private fun MatchListItem(match: MatchDetailDisplayModel) {
    val blueTeamName = match.blueTeamResult.team.name
    val orangeTeamName = match.orangeTeamResult.team.name

    Card(
        shape = CutCornerShape(
            0.dp,
            0.dp,
            8.dp,
            8.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = PocketLeagueTheme.sizes.screenPadding,
            ),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
        ) {
            RemoteImage(
                imageUrl = match.blueTeamResult.team.imageUrl.lightThemeImageURL.orEmpty(),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp),
            )

            Spacer(
                modifier = Modifier
                    .weight(1F),
            )

            RemoteImage(
                imageUrl = match.orangeTeamResult.team.imageUrl.lightThemeImageURL.orEmpty(),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp),
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.horizontalStageSection(
    state: EventDetailScreen.State,
) {
    val stages = state.event.stageSummaries

    item {
        val pagerState = rememberPagerState()
        val pageSpacingDp = PocketLeagueTheme.sizes.listItemSpacing

        LaunchedEffect(Unit) {
            snapshotFlow {
                pagerState.currentPage
            }.onEach { pageIndex ->
                val uiEvent = EventDetailScreen.Event.StageSelected(
                    stageIndex = pageIndex,
                )
                state.eventSink.invoke(uiEvent)
            }.launchIn(this)
        }

        HorizontalPager(
            pageCount = stages.size,
            pageSpacing = PocketLeagueTheme.sizes.listItemSpacing,
            contentPadding = PaddingValues(
                horizontal = pageSpacingDp.times(2),
            ),
            state = pagerState,
        ) { pageIndex ->
            val stage = stages[pageIndex]

            EventStageCard(
                eventStage = stage,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}
