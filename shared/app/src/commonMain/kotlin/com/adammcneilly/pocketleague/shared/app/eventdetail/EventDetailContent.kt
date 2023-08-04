package com.adammcneilly.pocketleague.shared.app.eventdetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.components.ListItemDividerCard

/**
 * UI representation of an [event].
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventDetailContent(
    event: EventDetailDisplayModel,
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
        horizontalStageSection(event)
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.horizontalStageSection(event: EventDetailDisplayModel) {
    val stages = event.getStageSummaries()

    item {
        val pagerState = rememberPagerState()
        val pageSpacingDp = PocketLeagueTheme.sizes.listItemSpacing

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

private fun LazyListScope.verticalStagesSection(event: EventDetailDisplayModel) {
    val stages = event.getStageSummaries()

    if (stages.isEmpty()) {
        return
    }

    item {
        EventDetailSectionHeader("Stages")
    }

    item {
        ListItemDividerCard(
            items = stages,
            modifier = Modifier
                .fillMaxWidth(),
        ) { eventStage ->
            EventStageListItem(
                eventStage = eventStage,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

@Composable
private fun EventDetailSectionHeader(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier,
    )
}
