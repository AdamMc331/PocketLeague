package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel

val TestDisplayModel.eventStageSummary: EventStageSummaryDisplayModel
    get() = EventStageSummaryDisplayModel(
        stageId = "stageId",
        name = "Playoffs",
        startDate = "Jan 01, 2000",
        endDate = "Jan 02, 2000",
        lan = false,
        liquipedia = "liquipediaURL",
    )
