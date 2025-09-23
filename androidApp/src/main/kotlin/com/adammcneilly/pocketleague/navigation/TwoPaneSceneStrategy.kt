package com.adammcneilly.pocketleague.navigation

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.Scene
import androidx.navigation3.ui.SceneStrategy
import androidx.window.core.layout.WindowSizeClass

/**
 * An implementation of [SceneStrategy] that determines if two [AppScreen] entries can appear in a
 * two pane scene together.
 */
class TwoPaneSceneStrategy : SceneStrategy<AppScreen> {
    @Composable
    @Suppress("ReturnCount")
    override fun calculateScene(
        entries: List<NavEntry<AppScreen>>,
        onBack: (Int) -> Unit,
    ): Scene<AppScreen>? {
        if (isMediumScreenWidthOrWider().value) {
            return null
        }

        val lastTwoEntries = entries.takeLast(2)

        if (lastTwoEntries.size != 2) {
            return null
        }

        val entriesSupportTwoPanes = lastTwoEntries.all { entry ->
            entry.metadata[TwoPaneScene.TWO_PANE_KEY] == true
        }

        if (!entriesSupportTwoPanes) {
            return null
        }

        val bothEntriesAreTabs = lastTwoEntries.all { entry ->
            entry.contentKey is AppScreen.Tab
        }

        if (bothEntriesAreTabs) {
            return null
        }

        val firstEntry = lastTwoEntries.first()
        val secondEntry = lastTwoEntries.last()

        return TwoPaneScene(
            key = Pair(firstEntry.contentKey, secondEntry.contentKey),
            previousEntries = entries.dropLast(1),
            firstEntry = firstEntry,
            secondEntry = secondEntry,
        )
    }
}

/**
 * Access the current [androidx.compose.material3.adaptive.WindowAdaptiveInfo] to determine if it's at
 * a medium or wider width class. This is used to determine whether
 * we should show a bottom navigation or a navigation rail.
 */
@Composable
private fun isMediumScreenWidthOrWider(): State<Boolean> {
    val isMediumScreenWidthOrWider = currentWindowAdaptiveInfo().windowSizeClass.isWidthAtLeastBreakpoint(
        widthDpBreakpoint = WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND,
    )

    return rememberUpdatedState(isMediumScreenWidthOrWider)
}
