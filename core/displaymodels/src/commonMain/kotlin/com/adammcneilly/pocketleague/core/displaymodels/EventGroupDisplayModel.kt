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
            val regionalGroup = mutableListOf<EventSummaryDisplayModel>()

            for (event in events) {
                if (event.isMajor) {
                    if (regionalGroup.isNotEmpty()) {
                        allGroups.add(Regionals(regionalGroup.toList()))
                        regionalGroup.clear()
                    }
                    allGroups.add(Major(event))
                } else {
                    regionalGroup.add(event)
                }
            }

            if (regionalGroup.isNotEmpty()) {
                allGroups.add(Regionals(regionalGroup.toList()))
            }

            return allGroups
        }
    }
}
