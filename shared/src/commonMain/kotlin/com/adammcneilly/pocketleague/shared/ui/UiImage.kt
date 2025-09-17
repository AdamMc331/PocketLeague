package com.adammcneilly.pocketleague.shared.ui

import org.jetbrains.compose.resources.DrawableResource

/**
 * Defines all variations of an image that can be rendered to a user.
 */
sealed interface UiImage {
    /**
     * Represents an image requested from a network [url].
     */
    data class Remote(
        val url: String,
    ) : UiImage

    /**
     * Represents a local image bundled with the app,
     * identified by [resource].
     */
    data class Local(
        val resource: DrawableResource,
    ) : UiImage
}
