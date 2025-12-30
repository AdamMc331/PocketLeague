package com.adammcneilly.pocketleague.shared.ui.scaffold.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.SatelliteAlt
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

/**
 * Defines a tab that will be shown on the home screen in a navigation container.
 */
enum class HomeTab(
    val label: String,
    val icon: ImageVector,
    val supportsTwoPane: Boolean,
) {
    @Serializable
    Events(
        label = "Events",
        icon = Icons.Default.Event,
        supportsTwoPane = false,
    ),
    Launches(
        label = "Launches",
        icon = Icons.Default.RocketLaunch,
        supportsTwoPane = true,
    ),
    Astronauts(
        label = "Astronauts",
        icon = Icons.Default.Groups,
        supportsTwoPane = true,
    ),
    Stations(
        label = "Stations",
        icon = Icons.Default.SatelliteAlt,
        supportsTwoPane = true,
    ),
}
