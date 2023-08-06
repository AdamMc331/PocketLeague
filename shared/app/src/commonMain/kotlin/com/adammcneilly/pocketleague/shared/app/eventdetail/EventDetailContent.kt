package com.adammcneilly.pocketleague.shared.app.eventdetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsBlue
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsOrange
import com.adammcneilly.pocketleague.shared.ui.components.RemoteImage
import com.adammcneilly.pocketleague.shared.ui.utils.conditional
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
        TeamResultRow(match)

        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            Box(
                modifier = Modifier
                    .weight(1F)
                    .height(4.dp)
                    .conditional(match.blueTeamResult.winner) {
                        background(color = rlcsBlue)
                    },
            )

            Text(
                text = match.localDate,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .weight(1F)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CutCornerShape(
                            topStart = 4.dp,
                            topEnd = 4.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp,
                        ),
                    ),
            )

            Box(
                modifier = Modifier
                    .weight(1F)
                    .height(4.dp)
                    .conditional(match.orangeTeamResult.winner) {
                        background(color = rlcsOrange)
                    },
            )
        }
    }
}

@Composable
private fun TeamResultRow(match: MatchDetailDisplayModel) {
    Row(
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RemoteImage(
            imageUrl = match.blueTeamResult.team.imageUrl.lightThemeImageURL.orEmpty(),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp),
        )

        Text(
            text = match.blueTeamResult.team.name,
            textAlign = TextAlign.Center,
            maxLines = 1,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.weight(1F),
        )

        Text(
            text = "${match.blueTeamResult.score}-${match.orangeTeamResult.score}",
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.weight(1F),
        )

        Text(
            text = match.orangeTeamResult.team.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            modifier = Modifier.weight(1F),
        )

        RemoteImage(
            imageUrl = match.orangeTeamResult.team.imageUrl.lightThemeImageURL.orEmpty(),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp),
        )
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
