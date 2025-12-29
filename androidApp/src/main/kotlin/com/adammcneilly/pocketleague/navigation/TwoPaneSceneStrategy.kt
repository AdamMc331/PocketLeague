package com.adammcneilly.pocketleague.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope

/**
 * An implementation of [SceneStrategy] that determines if two [AppScreen] entries can appear in a
 * two pane scene together.
 */
class TwoPaneSceneStrategy(
    private val isMediumOrLargerWidth: Boolean,
) : SceneStrategy<AppScreen> {
    @Suppress("ReturnCount")
    override fun SceneStrategyScope<AppScreen>.calculateScene(
        entries: List<NavEntry<AppScreen>>,
    ): Scene<AppScreen>? {
        if (!isMediumOrLargerWidth) {
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
