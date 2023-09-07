package com.adammcneilly.pocketleague.shared.ui.components

/**
 * Defines the configuration for a section of items within a list that can be
 * collapsed or expanded.
 */
data class CollapsibleSectionConfig<T>(
    val title: String,
    val items: List<T>,
    val isExpanded: Boolean,
)
