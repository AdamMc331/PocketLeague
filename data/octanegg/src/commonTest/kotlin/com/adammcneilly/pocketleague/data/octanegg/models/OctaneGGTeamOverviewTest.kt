package com.adammcneilly.pocketleague.data.octanegg.models

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class OctaneGGTeamOverviewTest {

    @Test
    fun convertDefault() {
        val octaneModel = OctaneGGTeamOverview()

        val domainModel = octaneModel.toTeam()

        with(domainModel) {
            assertEquals("", id)
            assertEquals("TBD", name)
            assertNull(lightThemeImageURL)
        }
    }

    @Test
    fun convertValidModel() {
        val octaneModel = OctaneGGTeamOverview(
            id = "someRandomId",
            name = "someRandomName",
            image = "someRandomImageUrl",
        )

        val domainModel = octaneModel.toTeam()

        with(domainModel) {
            assertEquals("someRandomId", id)
            assertEquals("someRandomName", name)
            assertEquals("someRandomImageUrl", lightThemeImageURL)
        }
    }
}
