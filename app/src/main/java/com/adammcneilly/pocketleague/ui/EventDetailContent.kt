@file:OptIn(ExperimentalMaterial3Api::class)

package com.adammcneilly.pocketleague.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.adammcneilly.pocketleague.android.designsystem.placeholder.cardPlaceholder
import com.adammcneilly.pocketleague.android.designsystem.placeholder.placeholderMaterial
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.screens.eventdetail.EventDetailViewState
import com.adammcneilly.pocketleague.ui.composables.components.TooltipChip
import com.adammcneilly.pocketleague.ui.composables.eventstage.StageSummaryListItem
import com.adammcneilly.pocketleague.ui.composables.team.TeamOverviewListItem
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.launch

private const val EVENT_IMAGE_ASPECT_RATIO = 3.0F

/**
 * Renders the [viewState] of detailed event information.
 */
@Composable
fun EventDetailContent(
    viewState: EventDetailViewState,
    onStageClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    // If display model is null, that means we had an error
    // and instead we should render an error UI.
    val displayModel = viewState.eventDetail ?: return

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        EventImageName(displayModel)

        EventDetails(displayModel)

        EventStages(
            displayModel,
            onStageClicked = onStageClicked,
        )

        val participants = viewState.participants

        if (participants?.isNotEmpty() == true) {
            EventParticipants(participants = participants)
        }
    }
}

@Composable
private fun EventParticipants(
    participants: List<TeamOverviewDisplayModel>,
) {
    Text(
        text = "Participants",
        style = MaterialTheme.typography.headlineSmall,
    )

    Card {
        participants.forEachIndexed { index, participant ->
            TeamOverviewListItem(
                displayModel = participant,
            )

            if (index != participants.lastIndex) {
                Divider()
            }
        }
    }
}

@Composable
private fun EventStages(
    displayModel: EventDetailDisplayModel,
    onStageClicked: (String, String) -> Unit,
) {
    Text(
        text = "Stages",
        style = MaterialTheme.typography.headlineSmall,
    )

    Card {
        with(displayModel.getStageSummaries()) {
            this.forEachIndexed { index, stageSummary ->
                StageSummaryListItem(
                    displayModel = stageSummary,
                    modifier = Modifier
                        .clickable {
                            onStageClicked.invoke(
                                displayModel.eventId,
                                stageSummary.stageId,
                            )
                        }
                )

                if (index != this.lastIndex) {
                    Divider()
                }
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
        if (displayModel.isPlaceholder) {
            PlaceholderDetails()
        } else {
            RealDetails(displayModel)
        }
    }
}

@Composable
private fun PlaceholderDetails() {
    (1..4).forEach {
        Box(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = 100.dp,
                    minHeight = 25.dp,
                )
                .placeholderMaterial(
                    visible = true,
                )
        )
    }
}

@Composable
private fun RealDetails(displayModel: EventDetailDisplayModel) {
    TooltipChip(
        text = displayModel.tier.name,
        tooltipText = displayModel.tier.description,
    )

    TooltipChip(
        text = displayModel.region.name,
        tooltipText = displayModel.region.description,
    )

    TooltipChip(
        text = displayModel.onlineOrLAN,
        tooltipText = "This event takes place over the internet.",
    )

    TooltipChip(
        text = "Mode: ${displayModel.mode}",
        tooltipText = "The number of players on each team.",
    )

    val prize = displayModel.prize

    if (prize != null) {
        TooltipChip(
            text = "Prize: ${prize.prizeAmount}",
            tooltipText = "This is the total prize pool for top finishers.",
        )
    }
}

@Composable
private fun EventImageName(displayModel: EventDetailDisplayModel) {
    val isDarkTheme = isSystemInDarkTheme()
    val coroutineScope = rememberCoroutineScope()
    val containerColor = remember { mutableStateOf(Color.Unspecified) }

    val cardColors = getCardColors(containerColor.value)

    Card(
        colors = cardColors,
    ) {
        // First we determine which image URL we want to load
        val imageUrl = if (isDarkTheme) {
            displayModel.darkThemeImageUrl
        } else {
            displayModel.lightThemeImageUrl
        }

        // Build the base image request to load that image URL.
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .allowHardware(false)
            .build()

        val imageLoader = LocalContext.current.imageLoader

        // Startup a coroutine that will load up the desired image, and make a request for the
        // container color and set it once loaded
        LaunchedEffect(displayModel) {
            coroutineScope.launch {
                val imageResult = imageLoader.execute(imageRequest)

                if (imageResult is SuccessResult) {
                    val imageDrawable = imageResult.drawable

                    getMutedColorFromBitmap(
                        bitmap = (imageDrawable as BitmapDrawable).bitmap,
                        isDarkTheme = isDarkTheme,
                        onColorGenerated = { generatedColor ->
                            containerColor.value = generatedColor
                        },
                    )
                }
            }
        }

        // Create image from the result above.
        Image(
            painter = rememberAsyncImagePainter(imageRequest.data),
            contentDescription = "Event Image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(EVENT_IMAGE_ASPECT_RATIO)
                .padding(12.dp)
                .cardPlaceholder(
                    visible = imageUrl == null,
                ),
        )

        Text(
            text = displayModel.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .cardPlaceholder(
                    visible = displayModel.isPlaceholder,
                ),
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

/**
 * If the supplied [containerColor] is unspecified, we want to return the default
 * card colors. Otherwise, we will use that as the container of the card.
 */
@Composable
private fun getCardColors(containerColor: Color): CardColors {
    return if (containerColor.isUnspecified) {
        CardDefaults.cardColors()
    } else {
        CardDefaults.cardColors(
            containerColor = containerColor
        )
    }
}

/**
 * This function consumes a [bitmap] that is the result of an async image loading request.
 *
 * We will generate a color palette from this image using the Palette API. We use the [isDarkTheme]
 * flag to determine which palette should be used.
 *
 * From there, we expose that result via the [onColorGenerated] callback, since the palette
 * generation is done asynchronously.
 */
private fun getMutedColorFromBitmap(
    bitmap: Bitmap,
    isDarkTheme: Boolean,
    onColorGenerated: (Color) -> Unit,
) {
    Palette.from(bitmap).generate { palette ->
        val rgb = if (isDarkTheme) {
            palette?.darkMutedSwatch?.rgb
        } else {
            palette?.lightMutedSwatch?.rgb
        }

        val color = rgb?.let(::Color) ?: Color.Unspecified

        onColorGenerated.invoke(color)
    }
}
