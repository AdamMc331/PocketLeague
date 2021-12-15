package com.adammcneilly.pocketleague.eventsummary.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary

interface GetUpcomingEventsUseCase {

    suspend operator fun invoke(): Result<List<EventSummary>>
}
