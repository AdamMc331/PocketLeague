package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.displaymodels.test.springInvitationalForAllRegions
import com.adammcneilly.pocketleague.core.displaymodels.test.worldChampionship
import kotlin.test.Test
import kotlin.test.assertEquals

class EventGroupDisplayModelTest {
    @Test
    fun mapFromListWithJustMajor() {
        val worlds = EventSummaryDisplayModel.worldChampionship()

        val expectedGroups = listOf(
            EventGroupDisplayModel.Major(worlds),
        )

        val output = EventGroupDisplayModel.mapFromEventList(listOf(worlds))

        assertEquals(expectedGroups, output)
    }

    @Test
    fun mapFromListWithJustRegionals() {
        val regionals = EventSummaryDisplayModel.springInvitationalForAllRegions()

        val expectedGroups = listOf(
            EventGroupDisplayModel.Regionals(regionals),
        )

        val output = EventGroupDisplayModel.mapFromEventList(regionals)

        assertEquals(expectedGroups, output)
    }

    @Test
    fun mapFromListEndingWithRegionals() {
        val worlds = EventSummaryDisplayModel.worldChampionship()
        val regionals = EventSummaryDisplayModel.springInvitationalForAllRegions()

        val expectedGroups = listOf(
            EventGroupDisplayModel.Major(worlds),
            EventGroupDisplayModel.Regionals(regionals),
        )

        val output = EventGroupDisplayModel.mapFromEventList(listOf(worlds) + regionals)

        assertEquals(expectedGroups, output)
    }

    @Test
    fun mapFromListEndingWithMajor() {
        val worlds = EventSummaryDisplayModel.worldChampionship()
        val regionals = EventSummaryDisplayModel.springInvitationalForAllRegions()

        val expectedGroups = listOf(
            EventGroupDisplayModel.Regionals(regionals),
            EventGroupDisplayModel.Major(worlds),
        )

        val output = EventGroupDisplayModel.mapFromEventList(regionals + worlds)

        assertEquals(expectedGroups, output)
    }
}
