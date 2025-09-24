package com.adammcneilly.pocketleague.shared.ui

import androidx.compose.runtime.compositionLocalOf

val LocalSceneType = compositionLocalOf {
    SceneType.Single
}

/**
 * Defines the type of scene that is being displayed.
 *
 * This is used to determine if we should show a single pane or a two-pane layout.
 */
enum class SceneType {
    Single,
    TwoPane,
}
