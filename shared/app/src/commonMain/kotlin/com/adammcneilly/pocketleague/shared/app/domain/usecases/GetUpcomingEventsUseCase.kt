package com.adammcneilly.pocketleague.shared.app.domain.usecases

import com.adammcneilly.pocketleague.shared.app.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.app.core.locale.LocaleHelper
import com.adammcneilly.pocketleague.shared.app.data.event.EventListRequest
import com.adammcneilly.pocketleague.shared.app.data.event.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Return an observable type of events that are upcoming from today's date.
 */
class GetUpcomingEventsUseCase(
    private val dateTimeFormatter: DateTimeFormatter,
    private val eventRepository: EventRepository,
    private val localeHelper: LocaleHelper,
    private val timeProvider: TimeProvider,
) {
    /**
     * @see [GetUpcomingEventsUseCase]
     */
    fun invoke(): Flow<List<EventGroupDisplayModel>> {
        val request = EventListRequest.AfterDate(
            dateUtc = timeProvider.now(),
        )

        return eventRepository
            .getEvents(request)
            .map { eventList ->
                eventList.map { event ->
                    EventSummaryDisplayModel(
                        event = event,
                        dateTimeFormatter = dateTimeFormatter,
                        localeHelper = localeHelper,
                    )
                }
            }
            .map(EventGroupDisplayModel.Companion::mapFromEventList)
    }
}
