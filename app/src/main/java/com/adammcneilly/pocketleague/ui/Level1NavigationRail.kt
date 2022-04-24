package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.screens.Level1Navigation
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenIdentifier

/**
 * A navigation rail is similar to a [Level1BottomBar] except it will show up on the left side of the
 * screen, used on foldable or other large screen devices.
 */
@Composable
fun Navigation.Level1NavigationRail(
    selectedTab: ScreenIdentifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Center
    ) {
        NavigationRailItem(
            icon = { Icon(Icons.Default.Feed, "Feed") },
            label = { Text("FEED") },
            selected = selectedTab.uri == Level1Navigation.Feed.screenIdentifier.uri,
            onClick = { navigateByLevel1Menu(Level1Navigation.Feed) },
        )

        NavigationRailItem(
            icon = { Icon(Icons.Default.BarChart, "Stats") },
            label = { Text("STATS") },
            selected = selectedTab.uri == Level1Navigation.Stats.screenIdentifier.uri,
            onClick = { navigateByLevel1Menu(Level1Navigation.Stats) },
        )

        NavigationRailItem(
            icon = { Icon(Icons.Default.Leaderboard, "Records") },
            label = { Text("RECORDS") },
            selected = selectedTab.uri == Level1Navigation.Records.screenIdentifier.uri,
            onClick = { navigateByLevel1Menu(Level1Navigation.Records) },
        )
    }
}

/**
 * Creates a single menu item to appear inside of a [Level1NavigationRail].
 */
@Composable
fun NavigationRailItem(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val selectedContentColor = MaterialTheme.colors.onPrimary
    val unselectedContentColor = selectedContentColor.copy(alpha = ContentAlpha.medium)

    val contentColor = if (selected) {
        selectedContentColor
    } else {
        unselectedContentColor
    }

    CompositionLocalProvider(
        LocalContentColor provides contentColor
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .padding(top = 25.dp, bottom = 25.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                icon()
                label()
            }
        }
    }
}
