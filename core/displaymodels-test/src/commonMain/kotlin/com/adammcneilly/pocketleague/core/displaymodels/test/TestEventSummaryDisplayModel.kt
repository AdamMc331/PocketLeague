package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL

fun EventSummaryDisplayModel.Companion.springInvitationalForRegion(
    region: String,
): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        eventId = "spring_invitational",
        imageURL = ThemedImageURL(),
        dateRange = "May 05 – 07, 2023",
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

fun EventSummaryDisplayModel.Companion.worldChampionship(): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        eventId = "world_championship",
        imageURL = ThemedImageURL(),
        dateRange = "August 03 - 13, 2023",
        name = "RLCS 2022-23 World Championship",
        arena = "PSD Bank Arena",
        location = "Düsseldorf, Germany",
        isMajor = true,
    )
}

fun EventSummaryDisplayModel.Companion.springMajor(): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        eventId = "spring_major",
        imageURL = ThemedImageURL(),
        dateRange = "July 06 - 09, 2023",
        name = "RLCS 2022-23 Spring Major",
        arena = "Agganis Arena",
        location = "Boston, USA",
        isMajor = true,
    )
}
