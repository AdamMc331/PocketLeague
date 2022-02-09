package com.adammcneilly.pocketleague.teamdetail.ui

import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.core.ui.FlagResProvider
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.player.ui.PlayerDisplayModel
import com.adammcneilly.pocketleague.player.ui.toDisplayModel

/**
 * A class that presents a team in a user friendly fashion.
 */
data class TeamDetailDisplayModel(
    val name: String,
    val logo: UIImage,
    val players: List<PlayerDisplayModel>,
)

/**
 * Converts a [Team] to a [TeamDetailDisplayModel].
 *
 * @param[flagResProvider] Used to determine the flag images for the players.
 */
fun Team.toDetailDisplayModel(
    flagResProvider: FlagResProvider,
): TeamDetailDisplayModel {
    return TeamDetailDisplayModel(
        name = this.name,
        logo = UIImage.Remote(this.lightThemeLogoImageUrl.orEmpty()),
        players = this.roster.map { player ->
            player.toDisplayModel(flagResProvider)
        },
    )
}
