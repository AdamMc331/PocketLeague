package com.adammcneilly.pocketleague.shared.ui.scaffold.app

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.adammcneilly.pocketleague.shared.ui.scaffold.navigation.HomeTab
import com.adammcneilly.pocketleague.shared.ui.scaffold.navigation.NavItem
import kotlinx.parcelize.Parcelize

/**
 * A composition local provider for [AppState] allows us to
 * access app wide navigation data from within any subscreen that
 * needs it.
 */
val LocalAppState = staticCompositionLocalOf<AppState> {
    throw IllegalArgumentException("AppState must be provided in the app scaffolding.")
}

/**
 * By extracting the parcelable components out of [AppState], this data
 * class can also be parcelable and persisted across configuration changes
 * using rememberSaveable.
 */
@Parcelize
data class AppStateData(
    val navItems: List<NavItem>,
) : Parcelable {
    constructor(
        selectedTab: HomeTab = HomeTab.Events,
    ) : this(
        navItems = HomeTab.entries.map { tab ->
            NavItem(
                tab = tab,
                selected = (tab == selectedTab),
            )
        },
    )
}

/**
 * The application state container, it's main purpose to expose the
 * shared business logic like navigation state via [navItems].
 */
class AppState(
    initialData: AppStateData = AppStateData(),
) {
    var navItems: List<NavItem> by mutableStateOf(initialData.navItems)
        private set

    val currentSelectedTab: HomeTab?
        get() = navItems
            .firstOrNull { navItem ->
                navItem.selected
            }?.tab

    fun onNavItemSelected(
        tab: HomeTab,
    ) {
        navItems = navItems.map { navItem ->
            navItem.copy(
                selected = (navItem.tab == tab),
            )
        }
    }

    /**
     * Convert this class into something that can actually be saved
     * in rememberSaveable.
     */
    fun toSaveableData(): AppStateData {
        return AppStateData(
            navItems = navItems,
        )
    }

    companion object {
        val saver = Saver<AppState, AppStateData>(
            save = { appState ->
                appState.toSaveableData()
            },
            restore = { appStateData ->
                AppState(appStateData)
            },
        )
    }
}
