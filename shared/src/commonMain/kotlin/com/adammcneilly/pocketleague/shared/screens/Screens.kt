package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.shared.screens.feed.initFeed
import com.adammcneilly.pocketleague.shared.screens.matchdetail.initMatchDetail
import com.adammcneilly.pocketleague.shared.screens.records.initRecords
import com.adammcneilly.pocketleague.shared.screens.stats.initStats

/**
 * An enumeration of all screens that appear somewhere in our application.
 */
enum class Screens(
    val asString: String,
    val navigationLevel: Int = 1,
    val initSettings: Navigation.(ScreenIdentifier) -> ScreenInitSettings,
    val stackableInstances: Boolean = false,
) {
    Feed(
        asString = "feed",
        navigationLevel = 1,
        initSettings = {
            initFeed()
        },
        stackableInstances = true,
    ),
    Stats(
        asString = "stats",
        navigationLevel = 1,
        initSettings = {
            initStats()
        },
        stackableInstances = true,
    ),
    Records(
        asString = "records",
        navigationLevel = 1,
        initSettings = {
            initRecords()
        },
        stackableInstances = true,
    ),
    MatchDetail(
        asString = "match_detail",
        navigationLevel = 2,
        initSettings = {
            initMatchDetail(it.params())
        },
        stackableInstances = false,
    )
}
