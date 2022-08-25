package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.shared.screens.eventdetail.EventDetailScreen
import com.adammcneilly.pocketleague.shared.screens.eventstagedetail.EventStageDetailScreen
import com.adammcneilly.pocketleague.shared.screens.feed.FeedScreen
import com.adammcneilly.pocketleague.shared.screens.matchdetail.MatchDetailScreen
import com.adammcneilly.pocketleague.shared.screens.records.RecordsScreen
import com.adammcneilly.pocketleague.shared.screens.stats.StatsScreen

/**
 * An enumeration of all screens that appear somewhere in our application.
 */
enum class AppScreens(
    val screen: Screen,
) {
    Feed(FeedScreen),
    Stats(StatsScreen),
    Records(RecordsScreen),
    MatchDetail(MatchDetailScreen),
    EventDetail(EventDetailScreen),
    EventStageDetail(EventStageDetailScreen),
}
