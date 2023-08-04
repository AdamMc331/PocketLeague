package com.adammcneilly.pocketleague.shared.app.eventdetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * UI representation of an [event].
 */
@Composable
fun EventDetailContent(
    event: EventDetailDisplayModel,
    selectedStage: EventStage.Id,
    matchesForSelectedStage: List<MatchDetailDisplayModel>,
    onStageSelected: (EventStage.Id) -> Unit,
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
        // TODO: Pass in selected stage?
        horizontalStageSection(event, onStageSelected)

        items(matchesForSelectedStage) { match ->
            val blueTeamName = match.blueTeamResult.team.name
            val orangeTeamName = match.orangeTeamResult.team.name

            Text(
                "$blueTeamName vs $orangeTeamName",
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.horizontalStageSection(
    event: EventDetailDisplayModel,
    onStageSelected: (EventStage.Id) -> Unit,
) {
    val stages = event.getStageSummaries().filterNot { it.isPlaceholder }

    if (stages.isEmpty()) {
        return
    }

    item {
        val pagerState = rememberPagerState()
        val pageSpacingDp = PocketLeagueTheme.sizes.listItemSpacing

        LaunchedEffect(Unit) {
            snapshotFlow {
                pagerState.currentPage
            }.map { pageIndex ->
                val stage = stages[pageIndex]
                stage.stageId
            }.onEach { stageId ->
                onStageSelected.invoke(stageId)
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

            // Based on page index, could we modify the card's full width?

            EventStageCard(
                eventStage = stage,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}
