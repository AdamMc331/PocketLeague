package com.adammcneilly.pocketleague.core.displaymodels

/**
 * A helper class that maintains a light and dark theme image URL in the same class.
 *
 * This helps keep classes organized and our UI module can provide a helper to pull the url we need.
 */
data class ThemedImageURL(
    val lightThemeImageURL: String? = null,
    val darkThemeImageURL: String? = lightThemeImageURL,
)
