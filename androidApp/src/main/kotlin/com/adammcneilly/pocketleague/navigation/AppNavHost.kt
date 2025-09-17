package com.adammcneilly.pocketleague.navigation

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import com.adammcneilly.pocketleague.shared.feature.eventlist.EventListScreen
import com.adammcneilly.pocketleague.shared.ui.scaffold.LocalNavAnimatedVisibilityScope
import com.adammcneilly.pocketleague.shared.ui.scaffold.app.LocalAppState
import com.adammcneilly.pocketleague.shared.ui.scaffold.navigation.HomeTab

@Composable
fun AppNavHost() {
    val startDestination = AppScreen.Tab(HomeTab.Events)

    val backStack = rememberNavBackStack<AppScreen>(
        startDestination,
    )

    val appState = LocalAppState.current

    val currentTab = appState.currentSelectedTab

    LaunchedEffect(currentTab) {
        if (currentTab != null) {
            val previousTab = (backStack.lastOrNull() as? AppScreen.Tab)?.tab
            if (previousTab != null) {
                if (currentTab != previousTab) {
                    // Before adding this tab, drop everything up to the first tab
                    while (backStack.lastOrNull() != startDestination) {
                        backStack.removeLastOrNull()
                    }

                    // Need to navigate to current tab
                    backStack.add(AppScreen.Tab(currentTab))
                }
            }
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()

            // If we're navigating back to a home tab, update app state.
            val newTab = (backStack.lastOrNull() as? AppScreen.Tab)?.tab
            if (newTab != null) {
                appState.onNavItemSelected(newTab)
            }
        },
        sceneStrategy = TwoPaneSceneStrategy(),
        entryProvider = { key ->
            when (key) {
                is AppScreen.Tab -> {
                    homeTabEntry(
                        key = key,
                        backStack = backStack,
                    )
                }
            }
        },
    )
}

private fun homeTabEntry(
    key: AppScreen.Tab,
    backStack: SnapshotStateList<AppScreen>,
): NavEntry<AppScreen> {
    val metadata = if (key.tab.supportsTwoPane) {
        TwoPaneScene.Companion.twoPane()
    } else {
        emptyMap()
    }

    return NavEntry(
        key = key,
        metadata = metadata,
    ) {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides LocalNavAnimatedContentScope.current,
        ) {
            when (key.tab) {
                HomeTab.Events -> {
                    EventListScreen()
                }

                HomeTab.Launches -> {
                    BasicText("Stub")
                }

                HomeTab.Astronauts -> {
                    BasicText("Stub")
                }

                HomeTab.Stations -> {
                    BasicText("Stub")
                }
            }
        }
    }
}
