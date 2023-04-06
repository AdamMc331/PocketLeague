package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.adammcneilly.pocketleague.android.designsystem.teamselection.TeamSelectionListItemClickListener
import com.adammcneilly.pocketleague.feature.event.detail.EventDetailParams
import com.adammcneilly.pocketleague.notifications.NotificationWorker
import com.adammcneilly.pocketleague.notifications.PocketLeagueNotificationManager
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenIdentifier
import com.adammcneilly.pocketleague.shared.screens.Screens
import com.adammcneilly.pocketleague.shared.screens.eventstagedetail.EventStageDetailParams
import com.adammcneilly.pocketleague.shared.screens.matchdetail.MatchDetailParams
import com.adammcneilly.pocketleague.shared.screens.myteams.updateTeamIsFavorite
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
 * The screen picker tacks a current [screenIdentifier] and renders the content for that screen.
 */
@Composable
@Suppress("LongMethod")
fun Navigation.ScreenPicker(
    screenIdentifier: ScreenIdentifier,
    paddingValues: PaddingValues = PaddingValues(),
) {
    val localContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    when (screenIdentifier.screen) {
        Screens.Feed -> {
            FeedContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
                onMatchClicked = { matchId ->
                    // Because we just want to test creating a notification
                    // from a match when it's clicked, we are doing the taboo solution
                    // of creating a blocking coroutine.
                    // BUT our end goal is to have workmanager fetch & schedule notifications, and that
                    // will handle threading properly.
                    coroutineScope.launch {
                        val matchEntity = stateProvider
                            .stateManager
                            .appModule
                            .dataModule
                            .matchRepository
                            .getMatchDetail(matchId)
                            .first()

                        val myWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
                            // We should replace this with a delay that is the time difference between now and when the match starts.
                            .setInitialDelay(5, TimeUnit.SECONDS)
                            .setInputData(
                                workDataOf(
                                    "title" to "${matchEntity.blueTeam.team.name} vs ${matchEntity.orangeTeam.team.name} Starting Now",
                                    "text" to "Click here to watch live!",
                                    "channelId" to PocketLeagueNotificationManager.MATCH_STARTED_CHANNEL_ID,
                                    "notificationId" to matchEntity.id.hashCode(),
                                ),
                            )
                            .build()

                        WorkManager.getInstance(localContext).enqueue(myWorkRequest)
                    }

//                    navigate(
//                        screen = Screens.MatchDetail,
//                        params = MatchDetailParams(
//                            matchId = matchId,
//                        ),
//                    )
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
                },
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
                        MatchDetailParams(matchId),
                    )
                },
            )
        }

        Screens.MyTeams -> {
            MyTeamsContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
                onAddTeamClicked = {
                    navigate(Screens.TeamSelection)
                },
            )
        }

        Screens.TeamSelection -> {
            TeamSelectionContent(
                viewState = stateProvider.get(screenIdentifier),
                listItemClickListener = object : TeamSelectionListItemClickListener {
                    override fun onFavoriteChanged(teamId: String, isFavorite: Boolean) {
                        events.updateTeamIsFavorite(teamId, isFavorite)
                    }
                },
                modifier = Modifier
                    .padding(paddingValues),
            )
        }
    }
}
