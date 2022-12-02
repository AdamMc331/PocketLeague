package com.adammcneilly.pocketleague.widgets

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel

data class UpcomingMatchesWidgetViewState(
    val matches: List<MatchDetailDisplayModel> = emptyList(),
)
