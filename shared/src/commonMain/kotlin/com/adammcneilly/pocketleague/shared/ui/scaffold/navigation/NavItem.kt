package com.adammcneilly.pocketleague.shared.ui.scaffold.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents an item in a navigation component, such as a tab bar.
 *
 * @property[tab] The specific tab that this item represents.
 * @property[selected] If true, this navigation item is currently selected.
 */
@Parcelize
data class NavItem(
    val tab: HomeTab,
    val selected: Boolean,
) : Parcelable
