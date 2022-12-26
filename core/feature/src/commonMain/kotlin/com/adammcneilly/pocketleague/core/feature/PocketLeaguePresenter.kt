package com.adammcneilly.pocketleague.core.feature

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

/**
 * Defines the core contract of a presenter in the Pocket League application.
 */
interface PocketLeaguePresenter<S : ScreenState> {

    /**
     * A reactive flow of the given [S] for this type of presenter.
     */
    val state: StateFlow<S>

    /**
     * Initializes this presenter using the supplied [scope] to make
     * any relevant data requests.
     */
    fun init(scope: CoroutineScope)
}
