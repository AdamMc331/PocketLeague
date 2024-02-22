package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.SwissTeamResult

/**
 * User friendly explanation of how a [team] preformed in a Swiss stage.
 *
 * @property[team] The team information for the players in this swiss result.
 * @property[overline] Describes if the team is qualified or eliminated. Should be empty if neither of those.
 * @property[subtitle] States how the team preformed in the swiss stage, with match and game records and game differential.
 *   example: 3-0 | 9-0 | +9
 */
data class SwissTeamResultDisplayModel(
    val team: TeamOverviewDisplayModel,
    val overline: String,
    val subtitle: String,
)

fun SwissTeamResult.toDisplayModel(): SwissTeamResultDisplayModel {
    return SwissTeamResultDisplayModel(
        team = this.team.toOverviewDisplayModel(),
        overline = "TODO",
        subtitle = "TODO",
    )
}
