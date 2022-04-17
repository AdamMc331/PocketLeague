package com.adammcneilly.pocketleague.ui

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import com.adammcneilly.pocketleague.shared.screens.Level1Navigation
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenIdentifier

@Composable
fun Navigation.Level1BottomBar(
    selectedTab: ScreenIdentifier
) {
    BottomAppBar(
        content = {
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Home, "Feed") },
                label = { Text("FEED") },
                selected = selectedTab.uri == Level1Navigation.Feed.screenIdentifier.uri,
                onClick = { navigateByLevel1Menu(Level1Navigation.Feed) }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Star, "Teams") },
                label = { Text("MY TEAMS") },
                selected = false,
                onClick = { },
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.AccountCircle, "Account") },
                label = { Text("ACCOUNT") },
                selected = false,
                onClick = { },
            )
        }
    )
}
