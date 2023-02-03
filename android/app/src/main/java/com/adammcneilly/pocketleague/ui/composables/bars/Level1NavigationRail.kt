package com.adammcneilly.pocketleague.ui.composables.bars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.Level1Navigation
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenIdentifier

/**
 * A navigation rail is similar to a [Level1BottomBar] except it will show up on the left side of the
 * screen, used on foldable or other large screen devices.
 *
 * @param[selectedTab] The current [ScreenIdentifier] that should be selected in the menu.
 * @param[modifier] Optional modifications to perform on this component.
 */
@Composable
fun Navigation.Level1NavigationRail(
    selectedTab: ScreenIdentifier,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier
    ) {
        NavigationRailItem(
            icon = { Icon(Icons.Default.Feed, "Feed") },
            label = { Text("FEED") },
            selected = selectedTab.uri == Level1Navigation.Feed.screenIdentifier.uri,
            onClick = { navigateByLevel1Menu(Level1Navigation.Feed) }
        )

        NavigationRailItem(
            icon = { Icon(Icons.Default.BarChart, "Stats") },
            label = { Text("STATS") },
            selected = selectedTab.uri == Level1Navigation.Stats.screenIdentifier.uri,
            onClick = { navigateByLevel1Menu(Level1Navigation.Stats) }
        )

        NavigationRailItem(
            icon = { Icon(Icons.Default.Leaderboard, "Records") },
            label = { Text("RECORDS") },
            selected = selectedTab.uri == Level1Navigation.Records.screenIdentifier.uri,
            onClick = { navigateByLevel1Menu(Level1Navigation.Records) }
        )
    }
}
