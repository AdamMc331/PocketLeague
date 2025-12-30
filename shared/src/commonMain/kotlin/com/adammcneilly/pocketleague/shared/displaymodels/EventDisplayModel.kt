package com.adammcneilly.pocketleague.shared.displaymodels

import com.adammcneilly.pocketleague.shared.models.EventType

data class EventDisplayModel(
    val id: Int,
    val name: String,
    val date: String,
    val time: String,
    val location: String,
    val isLive: Boolean,
    val numTeams: Int,
    val prize: String,
    val type: EventType,
)
