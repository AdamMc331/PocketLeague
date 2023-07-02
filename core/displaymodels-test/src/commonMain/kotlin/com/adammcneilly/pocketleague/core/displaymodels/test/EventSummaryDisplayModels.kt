package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL

fun EventSummaryDisplayModel.Companion.springInvitationalForRegion(
    region: String,
): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        eventId = "spring_invitational",
        imageURL = ThemedImageURL(),
        startDate = "May 05, 2023",
        endDate = "May 07, 2023",
        name = "$region Spring Invitational",
    )
}

fun EventSummaryDisplayModel.Companion.springInvitationalForAllRegions(): List<EventSummaryDisplayModel> {
    return listOf(
        "NA",
        "EU",
        "SAM",
        "SSA",
        "APAC",
        "MENA",
    ).map { region ->
        EventSummaryDisplayModel.springInvitationalForRegion(region)
    }
}
