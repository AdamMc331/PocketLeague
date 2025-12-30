package com.adammcneilly.pocketleague.test.paparazzi.feature.eventlist

import com.adammcneilly.pocketleague.shared.feature.eventlist.EventListHeader
import com.adammcneilly.pocketleague.shared.models.EventFilter
import com.adammcneilly.pocketleague.test.paparazzi.BasePaparazziTest
import kotlin.test.Test

class EventListHeaderPaparazziTest : BasePaparazziTest() {
    @Test
    fun renderDefault() {
        snapshot(
            screenPaddingDp = 0,
        ) {
            EventListHeader(
                selectedFilter = EventFilter.All,
                onFilterSelected = {},
            )
        }
    }
}
