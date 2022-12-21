package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Prize
import com.adammcneilly.pocketleague.sqldelight.LocalEvent
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

class LocalEventMappersTest {

    @Test
    fun convertFromLocalEventWithPrize() {
        val localEvent = LocalEvent(
            id = "1234",
            name = "name",
            startDateUTC = "startDateUTC",
            endDateUTC = "endDateUTC",
            imageURL = "imageURL",
            tier = "S",
            mode = "3",
            region = "NA",
            lan = false,
            prizeAmount = 10.0,
            prizeCurrency = "USD",
        )

        val domainEvent = localEvent.toEvent()

        with(domainEvent) {
            assertEquals("1234", this.id)
            assertEquals("name", this.name)
            assertEquals("startDateUTC", this.startDateUTC)
            assertEquals("endDateUTC", this.endDateUTC)
            assertEquals("imageURL", this.imageURL)
            assertEquals(EventTier.S, this.tier)
            assertEquals("3", this.mode)
            assertEquals(EventRegion.NA, this.region)
            assertFalse(this.lan)
            assertEquals(Prize(10.0, "USD"), this.prize)
        }
    }

    @Test
    fun convertFromLocalEventWithoutPrize() {
        val localEvent = LocalEvent(
            id = "1234",
            name = "name",
            startDateUTC = "startDateUTC",
            endDateUTC = "endDateUTC",
            imageURL = "imageURL",
            tier = "S",
            mode = "3",
            region = "NA",
            lan = false,
            prizeAmount = null,
            prizeCurrency = null,
        )

        val domainEvent = localEvent.toEvent()

        with(domainEvent) {
            assertEquals("1234", this.id)
            assertEquals("name", this.name)
            assertEquals("startDateUTC", this.startDateUTC)
            assertEquals("endDateUTC", this.endDateUTC)
            assertEquals("imageURL", this.imageURL)
            assertEquals(EventTier.S, this.tier)
            assertEquals("3", this.mode)
            assertEquals(EventRegion.NA, this.region)
            assertFalse(this.lan)
            assertNull(this.prize)
        }
    }
}
