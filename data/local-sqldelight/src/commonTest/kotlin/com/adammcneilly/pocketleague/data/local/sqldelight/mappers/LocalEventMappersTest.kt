package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Prize
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.sqldelight.LocalEvent
import com.varabyte.truthish.assertThat
import kotlin.test.Test

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
            assertThat(this.id.id).isEqualTo("1234")
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
            assertThat(this.id.id).isEqualTo("1234")
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

    @Test
    fun convertToLocalEvent() {
        val domainEvent = TestModel.event

        val localEvent = domainEvent.toLocalEvent()

        with(localEvent) {
            assertThat(this.id).isEqualTo(domainEvent.id.id)
            assertThat(this.name).isEqualTo(domainEvent.name)
            assertThat(this.startDateUTC).isEqualTo(domainEvent.startDateUTC)
            assertThat(this.endDateUTC).isEqualTo(domainEvent.endDateUTC)
            assertThat(this.imageURL).isEqualTo(domainEvent.imageURL)
            assertThat(this.tier).isEqualTo(domainEvent.tier.name)
            assertThat(this.mode).isEqualTo(domainEvent.mode)
            assertThat(this.region).isEqualTo(domainEvent.region.name)
            assertThat(this.lan).isEqualTo(domainEvent.lan)
            assertThat(this.prizeAmount).isEqualTo(domainEvent.prize?.amount)
            assertThat(this.prizeCurrency).isEqualTo(domainEvent.prize?.currency)
        }
    }
}
