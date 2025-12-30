package com.adammcneilly.pocketleague.shared.navigation

import androidx.navigation3.runtime.NavKey
import com.adammcneilly.pocketleague.shared.ui.scaffold.navigation.HomeTab
import kotlinx.serialization.Serializable

/**
 * Defines the different types of screens that can be displayed in the application.
 * This is used by the nav 3 back stack to provide a type safe wrapper around the
 * list of back stack entries.
 */
sealed class AppScreen : NavKey {
    /**
     * Defines one of the home tabs accessible by a navigation bar. The [tab] property
     * defines the [HomeTab] to display.
     */
    @Serializable
    data class Tab(
        val tab: HomeTab,
    ) : AppScreen()
}
