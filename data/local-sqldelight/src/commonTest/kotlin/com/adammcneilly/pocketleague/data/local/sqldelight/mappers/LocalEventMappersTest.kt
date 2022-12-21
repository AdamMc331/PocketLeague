package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Prize
import com.adammcneilly.pocketleague.sqldelight.LocalEvent
import com.varabyte.truthish.assertThat
import org.junit.Test

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
            assertThat(this.id).isEqualTo("1234")
            assertThat(this.name).isEqualTo("name")
            assertThat(this.startDateUTC).isEqualTo("startDateUTC")
            assertThat(this.endDateUTC).isEqualTo("endDateUTC")
            assertThat(this.imageURL).isEqualTo("imageURL")
            assertThat(this.tier).isEqualTo(EventTier.S)
            assertThat(this.mode).isEqualTo("3")
            assertThat(this.region).isEqualTo(EventRegion.NA)
            assertThat(this.lan).isFalse()
            assertThat(this.stages).isEmpty()
            assertThat(this.prize).isEqualTo(Prize(10.0, "USD"))
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
            assertThat(this.id).isEqualTo("1234")
            assertThat(this.name).isEqualTo("name")
            assertThat(this.startDateUTC).isEqualTo("startDateUTC")
            assertThat(this.endDateUTC).isEqualTo("endDateUTC")
            assertThat(this.imageURL).isEqualTo("imageURL")
            assertThat(this.tier).isEqualTo(EventTier.S)
            assertThat(this.mode).isEqualTo("3")
            assertThat(this.region).isEqualTo(EventRegion.NA)
            assertThat(this.lan).isFalse()
            assertThat(this.stages).isEmpty()
            assertThat(this.prize).isNull()
        }
    }
}
