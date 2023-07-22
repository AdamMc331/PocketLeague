package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import kotlin.test.Test
import kotlin.test.assertEquals

class OctaneGGEventTest {

    @Test
    fun mapFromValidEvent() {
        val octaneEvent = OctaneGGEvent(
            id = "eventId",
            endDateUTC = "endDate",
            imageURL = "image",
            mode = 1,
            name = "name",
            prize = OctaneGGPrize(),
            region = "NA",
            slug = "slug",
            stages = listOf(OctaneGGStage()),
            startDateUTC = "startDate",
            tier = "S",
            groups = listOf("testGroup"),
            lan = false,
        )

        val expectedEvent = Event(
            id = Event.Id("eventId"),
            endDateUTC = "endDate",
            imageURL = "image",
            mode = "1",
            name = "name",
            prize = OctaneGGPrize().toPrize(),
            region = EventRegion.NA,
            stages = listOf(OctaneGGStage().toEventStage()),
            startDateUTC = "startDate",
            tier = EventTier.S,
            lan = false,
        )

        val domainEvent = octaneEvent.toEvent()

        assertEquals(
            expected = expectedEvent,
            actual = domainEvent,
        )
    }

    @Test
    fun mapFromDefaultEvent() {
        val octaneEvent = OctaneGGEvent()

        val expectedEvent = Event(
            id = Event.Id(""),
            name = "",
            startDateUTC = null,
            endDateUTC = null,
            imageURL = null,
            stages = emptyList(),
            tier = EventTier.Unknown,
            mode = "",
            region = EventRegion.Unknown,
            lan = false,
            prize = null,
        )

        val domainEvent = octaneEvent.toEvent()

        assertEquals(
            expected = expectedEvent,
            actual = domainEvent,
        )
    }

    @Test
    fun mapEventTiers() {
        assertEquals(
            expected = EventTier.S,
            actual = "S".toEventTier(),
        )

        assertEquals(
            expected = EventTier.A,
            actual = "A".toEventTier(),
        )

        assertEquals(
            expected = EventTier.B,
            actual = "B".toEventTier(),
        )

        assertEquals(
            expected = EventTier.C,
            actual = "C".toEventTier(),
        )

        assertEquals(
            expected = EventTier.D,
            actual = "D".toEventTier(),
        )

        assertEquals(
            expected = EventTier.Unknown,
            actual = "???".toEventTier(),
        )

        assertEquals(
            expected = EventTier.Unknown,
            actual = null.toEventTier(),
        )
    }

    @Test
    fun mapEventRegion() {
        assertEquals(
            expected = EventRegion.NA,
            actual = "NA".toEventRegion(),
        )

        assertEquals(
            expected = EventRegion.EU,
            actual = "EU".toEventRegion(),
        )

        assertEquals(
            expected = EventRegion.OCE,
            actual = "OCE".toEventRegion(),
        )

        assertEquals(
            expected = EventRegion.SAM,
            actual = "SAM".toEventRegion(),
        )

        assertEquals(
            expected = EventRegion.ASIA,
            actual = "ASIA".toEventRegion(),
        )

        assertEquals(
            expected = EventRegion.ME,
            actual = "ME".toEventRegion(),
        )

        assertEquals(
            expected = EventRegion.INT,
            actual = "INT".toEventRegion(),
        )

        assertEquals(
            expected = EventRegion.Unknown,
            actual = "???".toEventRegion(),
        )

        assertEquals(
            expected = EventRegion.Unknown,
            actual = null.toEventRegion(),
        )
    }
}
