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
            assertEquals("Jul 07, 2023", dateRange)
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

    @Test
    fun parseDateRangeInSameMonthAndYear() {
        val event = TestModel.event.copy(
            startDateUTC = "2022-08-03T23:00:00Z",
            endDateUTC = "2022-08-05T23:00:00Z",
        )

        val displayModel = event.toSummaryDisplayModel()

        assertThat(displayModel.dateRange).isEqualTo("Aug 03 – 05, 2022")
    }

    @Test
    fun parseDateRangeInSameYear() {
        val event = TestModel.event.copy(
            startDateUTC = "2022-08-31T23:00:00Z",
            endDateUTC = "2022-09-02T23:00:00Z",
        )

        val displayModel = event.toSummaryDisplayModel()

        assertThat(displayModel.dateRange).isEqualTo("Aug 31 – Sep 02, 2022")
    }

    @Test
    fun parseDateRangeInDifferentYears() {
        val event = TestModel.event.copy(
            startDateUTC = "2022-12-31T23:00:00Z",
            endDateUTC = "2023-01-05T23:00:00Z",
        )

        val displayModel = event.toSummaryDisplayModel()

        assertThat(displayModel.dateRange).isEqualTo("Dec 31, 2022 – Jan 05, 2023")
    }

    @Test
    fun parseDateRangeOnSameDay() {
        val event = TestModel.event.copy(
            startDateUTC = "2022-12-31T13:00:00Z",
            endDateUTC = "2022-12-31T23:00:00Z",
        )

        val displayModel = event.toSummaryDisplayModel()

        assertThat(displayModel.dateRange).isEqualTo("Dec 31, 2022")
    }
}
