package com.adammcneilly.pocketleague.dkmp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.android.design.components.togglebutton.ToggleButtonOption
import com.adammcneilly.pocketleague.android.design.components.togglebutton.ToggleButtonRow
import com.adammcneilly.pocketleague.core.ui.CenteredMaterial3CircularProgressIndicator
import com.adammcneilly.pocketleague.eventsummary.EventSummaryListSort
import com.adammcneilly.pocketleague.eventsummary.ui.EventSummaryList
import com.adammcneilly.pocketleague.shared.eventsummarylist.EventSummaryListState

/**
 * Displays the content of an event summary list based on the supplied [viewState].
 */
@Composable
fun EventSummaryListScreen(
    viewState: EventSummaryListState,
    eventClicked: (String) -> Unit,
    onSortChanged: (EventSummaryListSort) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (viewState.showLoading) {
                CenteredMaterial3CircularProgressIndicator()
            }

            Column {
//            EventSummaryListSortToggle(
//                selectedSort = viewState.currentSort,
//                onSortChanged = onSortChanged,
//                modifier = Modifier
//                    .padding(16.dp),
//            )

                if (viewState.events.isNotEmpty()) {
                    EventSummaryList(
                        displayModels = viewState.events,
                        eventClicked = eventClicked,
                    )
                }
            }

            val errorMessage = viewState.errorMessage

            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                )
            }
        }
    }
}

@Composable
private fun EventSummaryListSortToggle(
    selectedSort: EventSummaryListSort,
    onSortChanged: (EventSummaryListSort) -> Unit,
    modifier: Modifier = Modifier,
) {
    val options = EventSummaryListSort.values().map { sort ->
        ToggleButtonOption(
            text = sort.displayText,
        )
    }

    val selectedOption = ToggleButtonOption(
        text = selectedSort.displayText,
    )

    ToggleButtonRow(
        options = options,
        selectedOption = selectedOption,
        modifier = modifier,
        onOptionSelected = { option ->
            val sort = EventSummaryListSort.values().find { sort ->
                sort.displayText == option.text
            }

            if (sort != null) {
                onSortChanged.invoke(sort)
            }
        }
    )
}
