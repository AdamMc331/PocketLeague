package com.adammcneilly.pocketleague.core.displaymodels

private const val PLACEHOLDER_LIST_COUNT = 3

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
        val placeholder = mapFromEventList(
            List(PLACEHOLDER_LIST_COUNT) {
                EventSummaryDisplayModel.placeholder
            },
        )

        /**
         * Given a collection of [events], convert them to a list of [EventGroupDisplayModel]
         * entities based on the event type.
         */
        fun mapFromEventList(events: List<EventSummaryDisplayModel>): List<EventGroupDisplayModel> {
            /**
             * An accumulation of event groups so we can perform a single iteration over
             * the supplied events using the fold function.
             *
             * @param[allGroups] All event groups calculated so far, which will be returned
             * at the end.
             * @param[currentRegionalList] The current grouping of regional events, which we will
             * either add to, or clear and move into allGroups, after each iteration.
             */
            @Suppress("OutdatedDocumentation") // This isn't outdated, detekt is confused?
            data class Accumulator(
                val allGroups: List<EventGroupDisplayModel>,
                val currentRegionalList: List<EventSummaryDisplayModel>,
            )

            val outputAccumulator = events.fold(Accumulator(emptyList(), emptyList())) { accumulator, event ->
                if (event.isMajor) {
                    val updatedAllGroups = buildList {
                        addAll(accumulator.allGroups)
                        if (accumulator.currentRegionalList.isNotEmpty()) {
                            add(Regionals(accumulator.currentRegionalList))
                        }
                        add(Major(event))
                    }

                    accumulator.copy(
                        allGroups = updatedAllGroups,
                        currentRegionalList = emptyList(),
                    )
                } else {
                    accumulator.copy(
                        currentRegionalList = accumulator.currentRegionalList + event,
                    )
                }
            }

            val finalGroups = buildList {
                addAll(outputAccumulator.allGroups)

                if (outputAccumulator.currentRegionalList.isNotEmpty()) {
                    add(Regionals(outputAccumulator.currentRegionalList))
                }
            }

            return finalGroups
        }
    }
}
