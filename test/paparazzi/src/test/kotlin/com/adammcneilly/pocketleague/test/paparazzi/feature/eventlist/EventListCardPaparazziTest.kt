package com.adammcneilly.pocketleague.test.paparazzi.feature.eventlist

import com.adammcneilly.pocketleague.shared.displaymodels.EventDisplayModel
import com.adammcneilly.pocketleague.shared.feature.eventlist.EventListCard
import com.adammcneilly.pocketleague.shared.models.EventType
import com.adammcneilly.pocketleague.test.paparazzi.BasePaparazziTest
import kotlin.test.Test

class EventListCardPaparazziTest : BasePaparazziTest() {
    @Test
    fun renderRegional() {
        val event = EventDisplayModel(
            id = "123",
            name = "NA Open #1",
            date = "Nov 14 - Dec 06, 2025",
            time = "",
            location = "Online",
            isLive = false,
            numTeams = 16,
            prize = $$"$133,200",
            type = EventType.REGIONAL,
        )

        snapshot {
            EventListCard(event)
        }
    }

    @Test
    fun renderMajor() {
        val event = EventDisplayModel(
            id = "123",
            name = "Boston Major",
            date = "Nov 14 - Dec 06, 2025",
            time = "",
            location = "Boston, Massachusetts, USA",
            isLive = false,
            numTeams = 16,
            prize = $$"$133,200",
            type = EventType.MAJOR,
        )

        snapshot {
            EventListCard(event)
        }
    }

    @Test
    fun renderWorlds() {
        val event = EventDisplayModel(
            id = "123",
            name = "RLCS 2025-26 World Championship",
            date = "Nov 14 - Dec 06, 2025",
            time = "",
            location = "Dickey's Arena",
            isLive = false,
            numTeams = 16,
            prize = $$"$133,200",
            type = EventType.WORLDS,
        )

        snapshot {
            EventListCard(event)
        }
    }
}
