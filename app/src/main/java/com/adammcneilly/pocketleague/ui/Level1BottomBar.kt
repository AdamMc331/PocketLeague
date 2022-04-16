package com.adammcneilly.pocketleague.ui

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
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
                icon = { Icon(Icons.Default.Menu, "Feed") },
                label = { Text("FEED", fontSize = 13.sp) },
                selected = selectedTab.uri == Level1Navigation.Feed.screenIdentifier.uri,
                onClick = { navigateByLevel1Menu(Level1Navigation.Feed) }
            )
        }
    )
}
