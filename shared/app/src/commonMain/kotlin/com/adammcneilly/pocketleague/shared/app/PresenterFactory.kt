package com.adammcneilly.pocketleague.shared.app

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.adammcneilly.pocketleague.feature.eventdetail.EventDetailPresenter
import com.adammcneilly.pocketleague.feature.eventdetail.EventDetailScreen
import com.adammcneilly.pocketleague.shared.app.match.MatchDetailScreen
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object PresenterFactory : Presenter.Factory, KoinComponent {

    private val eventRepository: EventRepository by inject()
    private val matchRepository: MatchRepository by inject()

    override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
        return when (screen) {
            is EventDetailScreen -> {
                EventDetailPresenter(
                    eventId = Event.Id(screen.eventId),
                    eventRepository = eventRepository,
                    matchRepository = matchRepository,
                    onMatchClicked = { matchId ->
                        navigator.goTo(MatchDetailScreen(matchId.id))
                    },
                )
            }
            else -> {
                null
            }
        }
    }
}
