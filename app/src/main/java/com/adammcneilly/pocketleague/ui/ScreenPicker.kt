package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenIdentifier
import com.adammcneilly.pocketleague.shared.screens.Screens
import com.adammcneilly.pocketleague.shared.screens.eventdetail.EventDetailParams
import com.adammcneilly.pocketleague.shared.screens.eventstagedetail.EventStageDetailParams
import com.adammcneilly.pocketleague.shared.screens.matchdetail.MatchDetailParams

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
        Screens.Feed -> {
            FeedContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
                onMatchClicked = { match ->
                    navigate(
                        screen = Screens.MatchDetail,
                        params = MatchDetailParams(
                            matchId = match.id,
                        ),
                    )
                },
                onEventClicked = { eventId ->
                    navigate(
                        screen = Screens.EventDetail,
                        params = EventDetailParams(
                            eventId = eventId,
                        ),
                    )
                },
            )
        }
        Screens.Stats -> {
            StatsContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
            )
        }
        Screens.Records -> {
            RecordsContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
            )
        }
        Screens.MatchDetail -> {
            MatchDetailContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
            )
        }
        Screens.EventDetail -> {
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
                        Screens.EventStageDetail,
                        params = params,
                    )
                }
            )
        }
        Screens.EventStageDetail -> {
            EventStageDetailContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
                onMatchClicked = { matchId ->
                    navigate(
                        Screens.MatchDetail,
                        MatchDetailParams(matchId)
                    )
                },
            )
        }
    }
}
