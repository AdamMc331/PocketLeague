package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.Location
import kotlin.test.Test
import kotlin.test.assertEquals

class OctaneGGStageTest {

    @Test
    fun mapFromValidStage() {
        val octaneStage = OctaneGGStage(
            id = 123,
            endDateUTC = "endDate",
            name = "name",
            startDateUTC = "startDate",
            prize = OctaneGGPrize(),
            location = OctaneGGLocation(),
            lan = false,
            liquipedia = "liquipedia",
            region = "NA",
            qualifier = false,
        )

        val expectedStage = EventStage(
            id = EventStage.Id("123"),
            name = "name",
            region = "NA",
            startDateUTC = "startDate",
            endDateUTC = "endDate",
            liquipedia = "liquipedia",
            qualifier = false,
            lan = false,
            location = Location(
                venue = "",
                city = "",
                countryCode = "",
            ),
        )

        assertEquals(
            expected = expectedStage,
            actual = octaneStage.toEventStage(),
        )
    }

    @Test
    fun mapFromDefaultStage() {
        val octaneStage = OctaneGGStage()

        val expectedStage = EventStage(
            id = EventStage.Id(""),
            name = "",
            region = "",
            startDateUTC = null,
            endDateUTC = null,
            liquipedia = "",
            qualifier = false,
            lan = false,
            location = null,
        )

        assertEquals(
            expected = expectedStage,
            actual = octaneStage.toEventStage(),
        )
    }
}
