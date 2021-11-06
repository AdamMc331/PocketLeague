package com.adammcneilly.pocketleague.teamdetail.ui

import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.player.ui.PlayerDisplayModel

data class TeamDetailDisplayModel(
    val name: String,
    val logo: UIImage,
    val players: List<PlayerDisplayModel>,
)
