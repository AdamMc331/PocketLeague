package com.adammcneilly.pocketleague.core.models

import kotlinx.datetime.Instant

/**
 * Defines information about a particular stage within an [Event].
 *
 * @property[id] A unique identifier for this stage.
 * @property[name] A user friendly description of the stage, such as Closed Qualifier.
 * @property[region] The region this stage applied to, like NA or EU.
 * @property[startDateUTC] The date this stage begins.
 * @property[endDateUTC] The date this stage will complete.
 * @property[liquipedia] A link to the Liquipedia Wiki for this stage.
 * @property[qualifier] True if this stage is a qualifying round to a main event.
 * @property[lan] True if this stage is happening at a LAN.
 */
data class EventStage(
    val id: String = "",
    val name: String = "",
    val region: String = "",
    val startDateUTC: Instant? = null,
    val endDateUTC: Instant? = null,
    val liquipedia: String = "",
    val qualifier: Boolean = false,
    val lan: Boolean = false,
)
