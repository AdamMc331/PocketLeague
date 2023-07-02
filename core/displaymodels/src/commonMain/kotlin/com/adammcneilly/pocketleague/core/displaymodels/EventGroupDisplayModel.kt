package com.adammcneilly.pocketleague.core.displaymodels

/**
 * Describes the various groups of events that can be shown inside the app.
 */
sealed class EventGroupDisplayModel {
    /**
     * For regional events, we want to show an entire list of [events].
     */
    data class Regionals(
        val events: List<EventSummaryDisplayModel>,
    ) : EventGroupDisplayModel()

    /**
     * For a major event, we will highlight that event by itself.
     */
    data class Major(
        val event: EventSummaryDisplayModel,
    ) : EventGroupDisplayModel()

    companion object {

        /**
         * Given a collection of [events], convert them to a list of [EventGroupDisplayModel]
         * entities based on the event type.
         */
        fun mapFromEventList(
            events: List<EventSummaryDisplayModel>,
        ): List<EventGroupDisplayModel> {
            val allGroups = mutableListOf<EventGroupDisplayModel>()
            var currentRegionalGroup = listOf<EventSummaryDisplayModel>()

            events.forEach { event ->
                if (event.isMajor) {
                    val regionalGroup = Regionals(currentRegionalGroup)
                    allGroups.add(regionalGroup)
                    currentRegionalGroup = emptyList()

                    allGroups.add(Major(event))
                } else {
                    currentRegionalGroup = currentRegionalGroup + event
                }
            }

            val regionalGroup = Regionals(currentRegionalGroup)
            allGroups.add(regionalGroup)
            currentRegionalGroup = emptyList()

            return allGroups
        }
    }
}
