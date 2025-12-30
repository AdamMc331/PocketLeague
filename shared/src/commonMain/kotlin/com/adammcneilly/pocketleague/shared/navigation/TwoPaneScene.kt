package com.adammcneilly.pocketleague.shared.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.Scene
import com.adammcneilly.pocketleague.shared.ui.LocalSceneType
import com.adammcneilly.pocketleague.shared.ui.SceneType

/**
 * Custom [Scene] implementation that will be used to render two [NavKey] entries
 * side by side, as long as they're supported by [TwoPaneSceneStrategy].
 */
class TwoPaneScene(
    override val key: Any,
    override val previousEntries: List<NavEntry<NavKey>>,
    val firstEntry: NavEntry<NavKey>,
    val secondEntry: NavEntry<NavKey>,
) : Scene<NavKey> {
    override val entries: List<NavEntry<NavKey>> = listOf(
        firstEntry,
        secondEntry,
    )

    override val content: @Composable (() -> Unit) = {
        CompositionLocalProvider(
            LocalSceneType provides SceneType.TwoPane,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .weight(1F),
                ) {
                    firstEntry.Content()
                }

                Box(
                    modifier = Modifier
                        .weight(1F),
                ) {
                    secondEntry.Content()
                }
            }
        }
    }

    companion object {
        const val TWO_PANE_KEY = "TwoPane"

        /**
         * Helper function to add metadata to a [NavEntry] indicating it can be displayed
         * in a two-pane layout.
         */
        fun twoPane() = mapOf(TWO_PANE_KEY to true)
    }
}
