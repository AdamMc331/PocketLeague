package com.adammcneilly.pocketleague.shared.eventlist

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.models.Event
import kotlinx.coroutines.flow.Flow

/**
 * Fetches a list of upcoming [Event] entities. This is not specific to any season, group, tier, etc.
 */
interface GetUpcomingEventsUseCase {

    /**
     * @see [GetUpcomingEventsUseCase].
     */
    operator fun invoke(): Flow<DataState<List<Event>>>
}
