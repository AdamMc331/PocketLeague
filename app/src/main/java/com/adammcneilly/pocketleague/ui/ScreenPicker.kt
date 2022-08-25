package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.feature.core.ScreenIdentifier
import com.adammcneilly.pocketleague.feature.feed.FeedScreen
import com.adammcneilly.pocketleague.shared.screens.AppScreens
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.eventdetail.EventDetailParams
import com.adammcneilly.pocketleague.shared.screens.eventdetail.EventDetailScreen
import com.adammcneilly.pocketleague.shared.screens.eventstagedetail.EventStageDetailParams
import com.adammcneilly.pocketleague.shared.screens.eventstagedetail.EventStageDetailScreen
import com.adammcneilly.pocketleague.shared.screens.matchdetail.MatchDetailParams
import com.adammcneilly.pocketleague.shared.screens.matchdetail.MatchDetailScreen
import com.adammcneilly.pocketleague.shared.screens.records.RecordsScreen
import com.adammcneilly.pocketleague.shared.screens.stats.StatsScreen

/**
 * The screen picker tacks a current [screenIdentifier] and renders the content for that screen.
 */
@Composable
@Suppress("LongMethod")
fun Navigation.ScreenPicker(
    screenIdentifier: ScreenIdentifier,
    paddingValues: PaddingValues = PaddingValues(),
) {

    when (screenIdentifier.screen) {
        is FeedScreen -> {
            FeedContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
                onMatchClicked = { match ->
                    navigate(
                        appScreen = AppScreens.MatchDetail,
                        params = MatchDetailParams(
                            matchId = match.id,
                        ),
                    )
                },
                onEventClicked = { eventId ->
                    navigate(
                        appScreen = AppScreens.EventDetail,
                        params = EventDetailParams(
                            eventId = eventId,
                        ),
                    )
                },
            )
        }
        StatsScreen -> {
            StatsContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
            )
        }
        RecordsScreen -> {
            RecordsContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
            )
        }
        MatchDetailScreen -> {
            MatchDetailContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
            )
        }
        EventDetailScreen -> {
            EventDetailContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
                onStageClicked = { eventId, stageId ->
                    val params = EventStageDetailParams(
                        eventId = eventId,
                        stageId = stageId,
                    )

                    navigate(
                        AppScreens.EventStageDetail,
                        params = params,
                    )
                }
            )
        }
        EventStageDetailScreen -> {
            EventStageDetailContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
                onMatchClicked = { matchId ->
                    navigate(
                        AppScreens.MatchDetail,
                        MatchDetailParams(matchId)
                    )
                },
            )
        }
    }
}
