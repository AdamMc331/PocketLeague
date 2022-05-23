@file:OptIn(ExperimentalMaterial3Api::class)

package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.screens.eventdetail.EventDetailViewState
import com.adammcneilly.pocketleague.ui.components.Chip
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.placeholder.material.placeholder

private const val EVENT_IMAGE_ASPECT_RATIO = 1.5F

/**
 * Renders the [viewState] of detailed event information.
 */
@Composable
fun EventDetailContent(
    viewState: EventDetailViewState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        val eventDetail = viewState.eventDetail

        if (eventDetail != null) {
            EventDetail(eventDetail)
        }
    }
}

@Composable
private fun EventDetail(
    displayModel: EventDetailDisplayModel,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        EventImageName(displayModel)

        EventDetails(displayModel)

        EventStages(displayModel)
    }
}

@Composable
private fun EventStages(displayModel: EventDetailDisplayModel) {
    Text(
        text = "Stages",
        style = MaterialTheme.typography.headlineSmall,
    )

    Card {
        displayModel.stageSummaries.forEachIndexed { index, stageSummary ->
            StageSummaryListItem(
                displayModel = stageSummary,
            )

            if (index != displayModel.stageSummaries.lastIndex) {
                Divider()
            }
        }
    }
}

@Composable
private fun EventDetails(displayModel: EventDetailDisplayModel) {
    Text(
        text = "Details",
        style = MaterialTheme.typography.headlineSmall,
    )

    FlowRow(
        mainAxisSpacing = 12.dp,
        crossAxisSpacing = 12.dp,
    ) {
        Chip(
            text = "Tier: ${displayModel.tier}",
        )

        Chip(
            text = "Region: ${displayModel.region}",
        )

        Chip(
            text = displayModel.onlineOrLAN,
        )

        Chip(
            text = "Mode: ${displayModel.mode}",
        )

        Chip(
            text = "Prize: ${displayModel.prize}",
        )
    }
}

@Composable
private fun EventImageName(displayModel: EventDetailDisplayModel) {
    Card {
        val imageUrl = if (isSystemInDarkTheme()) {
            displayModel.darkThemeImageUrl
        } else {
            displayModel.lightThemeImageUrl
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Event Image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(EVENT_IMAGE_ASPECT_RATIO)
                .padding(12.dp)
                .placeholder(
                    visible = imageUrl == null,
                    color = MaterialTheme.colorScheme.inverseSurface,
                ),
        )

        Text(
            text = displayModel.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun EventDetailContentPreview() {
    val eventDetail = EventDetailDisplayModel(
        eventId = "123",
        name = "RLCS 2021-22 Spring North America Regional 3",
        startDate = "May 21, 2022",
        endDate = "May 29, 2022",
        stageSummaries = listOf(
            EventStageSummaryDisplayModel(
                stageId = "123",
                name = "Closed Qualifier",
                startDate = "May 22, 2022",
                endDate = "May 23, 2022",
            ),
            EventStageSummaryDisplayModel(
                stageId = "123",
                name = "Main Event",
                startDate = "May 27, 2022",
                endDate = "May 29, 2022",
            )
        )
    )

    val viewState = EventDetailViewState(
        eventId = "123",
        showLoading = false,
        eventDetail = eventDetail,
    )

    PocketLeagueTheme {
        Surface {
            EventDetailContent(
                viewState = viewState,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
