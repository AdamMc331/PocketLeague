package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.Event
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class EventSummaryDisplayModelTest {

    @Test
    fun convertFromEvent() {
        val testEvent = Event(
            id = "1234",
            name = "Test Event",
            startDate = Instant.parse("2022-01-01T12:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
            endDate = Instant.parse("2022-01-02T12:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
            imageUrl = "Test Image URL",
        )

        val displayModel = testEvent.toSummaryDisplayModel()

        with(displayModel) {
            assertEquals("1234", eventId)
            assertEquals("Test Image URL", lightThemeImageUrl)
            assertEquals("Test Image URL", darkThemeImageUrl)
            assertEquals("Jan 01, 2022", startDate)
            assertEquals("Test Event", name)
        }
    }
}
