package com.adammcneilly.pocketleague.eventoverview.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.shared.core.ui.UIText
import com.adammcneilly.pocketleague.shared.eventoverview.EventOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.eventoverview.EventOverviewViewState
import com.adammcneilly.pocketleague.shared.standings.StandingsDisplayModel
import org.junit.Rule
import org.junit.Test

class EventOverviewContentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun renderLoadingState() {
        val viewState = EventOverviewViewState(
            showLoading = true,
        )

        composeTestRule.setContent {
            EventOverviewContent(
                viewState = viewState,
                onPhaseClicked = {},
            )
        }

        composeTestRule
            .onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate))
            .assertIsDisplayed()
    }

    @Test
    fun renderErrorMessage() {
        val errorMessage = "Whoops"

        val viewState = EventOverviewViewState(
            showLoading = false,
            errorMessage = UIText.StringText(errorMessage),
        )

        composeTestRule.setContent {
            EventOverviewContent(
                viewState = viewState,
                onPhaseClicked = {},
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertIsDisplayed()
    }

    @Test
    fun renderEmptyStates() {
        val eventName = "Open Qualifier"
        val startDate = "Feb 11, 2022"

        val eventOverview = EventOverviewDisplayModel(
            eventName = eventName,
            startDate = startDate,
            phases = emptyList(),
            standings = StandingsDisplayModel(
                placements = emptyList(),
            ),
        )

        val viewState = EventOverviewViewState(
            showLoading = false,
            event = eventOverview,
        )

        composeTestRule.setContent {
            EventOverviewContent(
                viewState = viewState,
                onPhaseClicked = {},
            )
        }

        composeTestRule
            .onNodeWithText(eventName)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(startDate)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(
                composeTestRule.activity.getString(R.string.bracket_information_unavailable)
            )
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(
                composeTestRule.activity.getString(R.string.standings_information_unavailable)
            )
            .assertIsDisplayed()
    }
}
