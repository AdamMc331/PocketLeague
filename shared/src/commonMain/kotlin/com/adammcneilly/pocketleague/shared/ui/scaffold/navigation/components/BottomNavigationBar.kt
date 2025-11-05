package com.adammcneilly.pocketleague.shared.ui.scaffold.navigation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.ui.scaffold.app.LocalAppState

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
) {
    val appState = LocalAppState.current

    NavigationBar(
        modifier = modifier,
    ) {
        appState.navItems.forEach { item ->
            NavigationBarItem(
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
