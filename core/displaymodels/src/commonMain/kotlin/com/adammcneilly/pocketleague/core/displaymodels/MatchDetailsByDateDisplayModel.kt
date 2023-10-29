package com.adammcneilly.pocketleague.core.displaymodels

/**
 * A display model to render [matchesByDate]. Instead of exposing the map itself, we expose
 * this type because [isPlaceholder] helps us render a better loading screen as we can provide placeholders
 * for the date too.
 */
data class MatchDetailsByDateDisplayModel(
    val matchesByDate: Map<String, List<MatchDetailDisplayModel>> = emptyMap(),
    val isPlaceholder: Boolean = false,
) {
    companion object {
        val placeholder: MatchDetailsByDateDisplayModel
            get() {
                val matchesByDate = (1..3).map {
                    MatchDetailDisplayModel.placeholder
                }.groupBy(MatchDetailDisplayModel::localDate)

                return MatchDetailsByDateDisplayModel(
                    matchesByDate = matchesByDate,
                    isPlaceholder = true,
                )
            }
    }
}
