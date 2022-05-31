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
import com.adammcneilly.pocketleague.shared.screens.Level1Navigation
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenIdentifier


/**
 * Creates a [NavigationBar] to manage the menu items on a phone or other compact screen size.
 */
@Composable
fun Navigation.Level1BottomBar(
    selectedTab: ScreenIdentifier,
    modifier: Modifier,
) {
    NavigationBar(
        content = {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Feed, "Feed") },
                label = { Text("FEED") },
                selected = selectedTab.uri == Level1Navigation.Feed.screenIdentifier.uri,
                onClick = { navigateByLevel1Menu(Level1Navigation.Feed) }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.BarChart, "Stats") },
                label = { Text("STATS") },
                selected = selectedTab.uri == Level1Navigation.Stats.screenIdentifier.uri,
                onClick = { navigateByLevel1Menu(Level1Navigation.Stats) },
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Leaderboard, "Records") },
                label = { Text("RECORDS") },
                selected = selectedTab.uri == Level1Navigation.Records.screenIdentifier.uri,
                onClick = { navigateByLevel1Menu(Level1Navigation.Records) },
            )
        },
        modifier = modifier,
    )
}