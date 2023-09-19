package com.adammcneilly.pocketleague.data.event.api

import com.adammcneilly.pocketleague.core.data.RemoteDataService
import com.adammcneilly.pocketleague.core.models.Event

/**
 * Defines a data contract for requesting necessary remote
 * information about events.
 */
interface RemoteEventService : RemoteDataService<EventListRequest, List<Event>>
