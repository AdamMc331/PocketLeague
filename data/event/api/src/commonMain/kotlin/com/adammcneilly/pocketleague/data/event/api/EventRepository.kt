package com.adammcneilly.pocketleague.data.event.api

import com.adammcneilly.pocketleague.core.data.Repository
import com.adammcneilly.pocketleague.core.models.Event

/**
 * Defines the data layer for any event related requests.
 */
interface EventRepository : Repository<EventListRequest, List<Event>>
