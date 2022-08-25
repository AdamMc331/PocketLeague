package com.adammcneilly.pocketleague.composables.bars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.feature.core.ScreenIdentifier
import com.adammcneilly.pocketleague.shared.screens.Level1Navigation
import com.adammcneilly.pocketleague.shared.screens.Navigation

/**
 * Creates a [NavigationBar] to manage the menu items on a phone or other compact screen size.
 *
 * @param[selectedTab] The current [ScreenIdentifier] that should be selected in the menu.
 * @param[modifier] Optional modifications to perform on this component.
 */
@Composable
fun Navigation.Level1BottomBar(
    selectedTab: ScreenIdentifier,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        content = {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Feed, "Feed") },
                label = { Text("FEED") },
                selected = selectedTab.uri == Level1Navigation.Feed.getScreenIdentifier(stateManager).uri,
                onClick = { navigateByLevel1Menu(Level1Navigation.Feed) }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.BarChart, "Stats") },
                label = { Text("STATS") },
                selected = selectedTab.uri == Level1Navigation.Stats.getScreenIdentifier(stateManager).uri,
                onClick = { navigateByLevel1Menu(Level1Navigation.Stats) },
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Leaderboard, "Records") },
                label = { Text("RECORDS") },
                selected = selectedTab.uri == Level1Navigation.Records.getScreenIdentifier(stateManager).uri,
                onClick = { navigateByLevel1Menu(Level1Navigation.Records) },
            )
        },
        modifier = modifier,
    )
}
