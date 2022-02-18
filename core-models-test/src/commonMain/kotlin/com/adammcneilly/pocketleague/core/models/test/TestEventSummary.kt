package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.EventSummary

val testEventSummary = EventSummary(
    id = "EventID",
    eventName = "EventName",
    tournamentName = "TournamentName",
    tournamentImageUrl = "TournamentImageURL",
    startDateEpochSeconds = 0L,
    numEntrants = 0,
    isOnline = false,
)
