package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.test.FakeDateTimeFormatter
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import kotlin.test.Test
import kotlin.test.assertEquals

class EventSummaryDisplayModelTest {

    @Test
    fun convertFromEvent() {
        val mockDateString = "mock date string"
        val testEvent = TestModel.event

        val dateTimeFormatter = FakeDateTimeFormatter().apply {
            mockResponseForUTCString(
                utcString = testEvent.startDateUTC.orEmpty(),
                response = mockDateString
            )
        }

        val displayModel = testEvent.toSummaryDisplayModel(dateTimeFormatter)

        with(displayModel) {
            assertEquals(testEvent.id, eventId)
            assertEquals(testEvent.imageURL, imageURL.lightThemeImageURL)
            assertEquals(testEvent.imageURL, imageURL.darkThemeImageURL)
            assertEquals(mockDateString, startDate)
            assertEquals(testEvent.name, name)
        }
    }
}
