package com.adammcneilly.pocketleague.shared.models

enum class EventFilter(
    val label: String,
) {
    All("All"),
    Live("Live"),
    Upcoming("Upcoming"),
    Major("Major"),
}
