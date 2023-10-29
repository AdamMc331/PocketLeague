package com.adammcneilly.pocketleague.core.datetime.test

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.datetime.TimeZone

class FakeDateTimeFormatter : DateTimeFormatter {
    private val mockResponses: MutableMap<String, String?> = mutableMapOf()

    override fun formatUTCString(
        utcString: String,
        formatPattern: String,
        timeZone: TimeZone,
    ): String? {
        return mockResponses[utcString]
    }

    fun mockResponseForUTCString(
        utcString: String,
        response: String?,
    ) {
        mockResponses[utcString] = response
    }
}
