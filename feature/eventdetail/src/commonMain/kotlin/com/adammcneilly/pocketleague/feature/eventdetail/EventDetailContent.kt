package com.adammcneilly.pocketleague.feature.eventdetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.utils.screenHorizontalPadding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * UI representation for detailed info about an event.
 */
@Composable
internal fun EventDetailContent(
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
            MatchListItem(
                match = match,
                modifier = Modifier
                    .screenHorizontalPadding(),
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
