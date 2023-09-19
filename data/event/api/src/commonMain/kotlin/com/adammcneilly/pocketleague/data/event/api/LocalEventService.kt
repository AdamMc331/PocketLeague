package com.adammcneilly.pocketleague.data.event.api

import com.adammcneilly.pocketleague.core.data.LocalDataService
import com.adammcneilly.pocketleague.core.models.Event

/**
 * Represents a local source of truth for the event domain.
 */
interface LocalEventService : LocalDataService<EventListRequest, List<Event>>
