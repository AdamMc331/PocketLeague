package com.adammcneilly.pocketleague.shared.ui.feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.shared.ui.event.EventSummaryListCard
import com.adammcneilly.pocketleague.shared.ui.event.LanEventSummaryCard
import com.adammcneilly.pocketleague.shared.ui.utils.screenHorizontalPadding

/**
 * For a given [displayModel], determine how to render
 * that collection for our [FeedContent].
 */
@Composable
fun FeedEventGroup(
    displayModel: EventGroupDisplayModel,
    modifier: Modifier = Modifier,
) {
    val groupModifier = modifier
        .screenHorizontalPadding()

    when (displayModel) {
        is EventGroupDisplayModel.Regionals -> {
            EventSummaryListCard(
                events = displayModel.events,
                modifier = groupModifier,
            )
        }

        is EventGroupDisplayModel.Major -> {
            LanEventSummaryCard(
                event = displayModel.event,
                modifier = groupModifier,
            )
        }
    }
}
