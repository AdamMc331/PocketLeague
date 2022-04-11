package com.adammcneilly.pocketleague.event.implementation.smashgg.mappers

import com.adammcneilly.pocketleague.core.models.EventOverview
import com.adammcneilly.pocketleague.core.models.Standings
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.EventOverviewFragment
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.StandingsPlacementFragment
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Converts an [EventOverviewFragment] from the Apollo domain into an [EventOverview] in ours.
 */
fun EventOverviewFragment.toEventOverview(): EventOverview {
    val summaryFragment = this.eventSummaryFragment

    return EventOverview(
        name = summaryFragment.name.orEmpty(),
        startDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        phases = this.phaseGroups
            ?.mapNotNull { phaseGroup ->
                phaseGroup?.phaseGroupFragment?.toPhaseOverview()
            }
            .orEmpty(),
        standings = Standings(
            placements = this.standings
                ?.nodes
                ?.mapNotNull { node ->
                    node
                        ?.standingsPlacementFragment
                        .let(StandingsPlacementFragment?::toStandingsPlacement)
                }
                .orEmpty()
        ),
        timeZone = TimeZone.UTC,
    )
}
