package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.EventSummary
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val testEventSummary = EventSummary(
    id = "EventID",
    eventName = "EventName",
    tournamentName = "TournamentName",
    tournamentImageUrl = "TournamentImageURL",
    startDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
    timeZone = TimeZone.currentSystemDefault(),
    numEntrants = 0,
    isOnline = false,
)
