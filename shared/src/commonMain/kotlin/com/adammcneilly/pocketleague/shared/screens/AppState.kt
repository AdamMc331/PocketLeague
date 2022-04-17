package com.adammcneilly.pocketleague.shared.screens

/**
 * Defines the overall state of our application. We just track the [recompositionIndex] and we can
 * modify this any time we want our application to be recomposed.
 */
data class AppState(
    val recompositionIndex: Int = 0,
) {
    /**
     * Returns the [Navigation] instance for the given [viewModel].
     */
    fun getNavigation(viewModel: DKMPViewModel): Navigation {
        return viewModel.navigation
    }
}
