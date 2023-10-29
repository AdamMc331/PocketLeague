package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.LocationDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL
import com.adammcneilly.pocketleague.core.models.Event

fun EventSummaryDisplayModel.Companion.springInvitationalForRegion(region: String): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        eventId = Event.Id("spring_invitational"),
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
        eventId = Event.Id("world_championship"),
        imageURL = ThemedImageURL(),
        dateRange = "August 03 - 13, 2023",
        name = "RLCS 2022-23 World Championship",
        location =
            LocationDisplayModel(
                venue = "PSD Bank Arena",
                cityCountry = "Düsseldorf, Germany",
            ),
        isMajor = true,
    )
}

fun EventSummaryDisplayModel.Companion.springMajor(): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        eventId = Event.Id("spring_major"),
        imageURL = ThemedImageURL(),
        dateRange = "July 06 - 09, 2023",
        name = "RLCS 2022-23 Spring Major",
        location =
            LocationDisplayModel(
                venue = "Agganis Arena",
                cityCountry = "Boston, USA",
            ),
        isMajor = true,
    )
}
