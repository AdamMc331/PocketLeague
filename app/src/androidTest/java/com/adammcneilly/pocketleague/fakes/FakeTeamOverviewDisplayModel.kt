package com.adammcneilly.pocketleague.fakes

import com.adammcneilly.pocketleague.shared.core.ui.UIImage
import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewDisplayModel

val fakeTeamOverviewDisplayModel = TeamOverviewDisplayModel(
    name = "",
    lightLogoImage = UIImage.Remote(""),
    darkLogoImage = UIImage.Remote(""),
    roster = emptyList(),
)
