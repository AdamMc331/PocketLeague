package com.adammcneilly.pocketleague.shared.eventsummarylist

import com.adammcneilly.pocketleague.shared.ScreenState

data class EventSummaryListState(
    val showLoading: Boolean = true,
) : ScreenState
