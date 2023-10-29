package com.adammcneilly.pocketleague.core.models

import kotlin.jvm.JvmInline

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
 * @property[lan] True if this stage is happening at a LAN event in person.
 * @property[location] The location of this stage if it is a [lan].
 */
data class EventStage(
    val id: Id,
    val name: String,
    val region: String,
    val startDateUTC: String?,
    val endDateUTC: String?,
    val liquipedia: String,
    val qualifier: Boolean,
    val lan: Boolean,
    val location: Location?,
) {
    /**
     * Type safety to ensure an Id string is being used for an [EventStage].
     */
    @JvmInline
    value class Id(
        val id: String,
    )
}
