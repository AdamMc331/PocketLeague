package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier

val testEvent = Event(
    id = "eventId",
    name = "eventName",
    startDateUTC = "eventStartDate",
    endDateUTC = "eventEndDate",
    imageURL = "eventImageURL",
    stages = listOf(testEventStage),
    tier = EventTier.Unknown,
    mode = "eventMode",
    region = EventRegion.Unknown,
    lan = false,
    prize = testPrize,
)
