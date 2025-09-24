package com.adammcneilly.pocketleague.shared.ui.scaffold.navigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.ui.scaffold.app.LocalAppState

@Composable
fun SideNavigationRail(
    modifier: Modifier = Modifier,
) {
    val appState = LocalAppState.current

    NavigationRail(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            appState.navItems.forEach { item ->
                NavigationRailItem(
                    selected = item.selected,
                    onClick = {
                        appState.onNavItemSelected(item.tab)
                    },
                    icon = {
                        Icon(
                            imageVector = item.tab.icon,
                            contentDescription = item.tab.label,
                        )
                    },
                )
            }
        }
    }
}
