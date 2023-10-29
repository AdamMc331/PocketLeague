package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.EventStage

val TestModel.eventStage: EventStage
    get() =
        EventStage(
            id = EventStage.Id("eventStageId"),
            name = "eventStageName",
            startDateUTC = "eventStageStartDate",
            endDateUTC = "eventStageEndDate",
            region = "eventStageRegion",
            liquipedia = "eventStageLiquipedia",
            qualifier = false,
            lan = false,
            location = null,
        )
