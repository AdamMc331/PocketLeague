package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier

val testEvent = Event(
    id = "614b6485f8090ec745286425",
    name = "RLCS 2021-22 World Championship",
    startDateUTC = "2022-08-03T23:00:00Z",
    endDateUTC = "2022-08-14T23:00:00Z",
    imageURL = "eventImageURL",
    stages = listOf(testEventStage),
    tier = EventTier.S,
    mode = "3",
    region = EventRegion.INT,
    lan = true,
    prize = testPrize,
)
