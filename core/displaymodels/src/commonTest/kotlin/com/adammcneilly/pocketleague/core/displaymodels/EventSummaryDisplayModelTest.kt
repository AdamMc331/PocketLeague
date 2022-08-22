package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.test.FakeDateTimeFormatter
import com.adammcneilly.pocketleague.core.models.test.testEvent
import kotlin.test.Test
import kotlin.test.assertEquals

class EventSummaryDisplayModelTest {

    @Test
    fun convertFromEvent() {
        val mockDateString = "mock date string"
        val testEvent = testEvent

        val dateTimeFormatter = FakeDateTimeFormatter().apply {
            mockResponseForUTCString(
                utcString = testEvent.startDateUTC.orEmpty(),
                response = mockDateString,
            )
        }

        val displayModel = testEvent.toSummaryDisplayModel(dateTimeFormatter)

        with(displayModel) {
            assertEquals(testEvent.id, eventId)
            assertEquals(testEvent.imageUrl, imageURL.lightThemeImageUrl)
            assertEquals(testEvent.imageUrl, imageURL.darkThemeImageURL)
            assertEquals(mockDateString, startDate)
            assertEquals(testEvent.name, name)
        }
    }
}
