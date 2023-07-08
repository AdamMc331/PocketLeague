package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.test.FakeDateTimeFormatter
import com.adammcneilly.pocketleague.core.locale.provideLocaleHelper
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.core.models.test.eventStage
import com.varabyte.truthish.assertThat
import kotlin.test.Test
import kotlin.test.assertEquals

class EventSummaryDisplayModelTest {

    @Test
    fun convertFromEventWithoutLocation() {
        val mockDateString = "Jul 07, 2023"
        val testEvent = TestModel.event

        val dateTimeFormatter = FakeDateTimeFormatter().apply {
            mockResponseForUTCString(
                utcString = testEvent.startDateUTC.orEmpty(),
                response = mockDateString,
            )

            mockResponseForUTCString(
                utcString = testEvent.endDateUTC.orEmpty(),
                response = mockDateString,
            )
        }

        val displayModel = testEvent.toSummaryDisplayModel(dateTimeFormatter)

        with(displayModel) {
            assertEquals(testEvent.id, eventId)
            assertEquals(testEvent.imageURL, imageURL.lightThemeImageURL)
            assertEquals(testEvent.imageURL, imageURL.darkThemeImageURL)
            assertEquals("Jul 07 – 07, 2023", dateRange)
            assertEquals(testEvent.name, name)
            assertThat(arenaLocation).isEmpty()
        }
    }

    @Test
    fun convertFromEventWithLocation() {
        val mockDateString = "Jul 07, 2023"
        val testLocation = TestModel.agganisArenaLocation
        val testEvent = TestModel.event.copy(
            stages = listOf(
                TestModel.eventStage.copy(
                    location = testLocation,
                ),
            ),
        )

        val dateTimeFormatter = FakeDateTimeFormatter().apply {
            mockResponseForUTCString(
                utcString = testEvent.startDateUTC.orEmpty(),
                response = mockDateString,
            )

            mockResponseForUTCString(
                utcString = testEvent.endDateUTC.orEmpty(),
                response = mockDateString,
            )
        }

        // Need to create FakeLocaleHelper still
        val countryName = provideLocaleHelper().getCountryDisplayName(testLocation.countryCode)
        val expectedLocation = "${testLocation.venue} – ${testLocation.city}, $countryName"

        val displayModel = testEvent.toSummaryDisplayModel(dateTimeFormatter)

        assertThat(displayModel.arenaLocation).isEqualTo(expectedLocation)
    }
}
