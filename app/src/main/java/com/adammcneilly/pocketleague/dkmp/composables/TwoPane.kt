package com.adammcneilly.pocketleague.dkmp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.Navigation

private const val FIRST_PANE_WEIGHT = 0.4F
private const val SECOND_PANE_WEIGHT = 0.6F

/**
 * A multi-pane UI to be used on large screens when the available space allows.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation.TwoPane(
    saveableStateHolder: SaveableStateHolder
) {
    val navigationLevelsMap = getNavigationLevelsMap(currentLevel1ScreenIdentifier)!!
    Scaffold(
//        topBar = { TopBar(getTitle(currentScreenIdentifier)) },
        content = {
            Row {
//                Column(
//                    Modifier
//                        .fillMaxHeight()
//                        .width(80.dp)
//                ) {
//                    Level1NavigationRail(selectedTab = navigationLevelsMap[1]!!)
//                }
                Column(
                    Modifier
                        .weight(FIRST_PANE_WEIGHT)
                ) {
                    saveableStateHolder.SaveableStateProvider(navigationLevelsMap[1]!!.uri) {
                        ScreenPicker(navigationLevelsMap[1]!!)
                    }
                }
                Column(
                    Modifier
                        .weight(SECOND_PANE_WEIGHT)
                        .padding(20.dp)
                ) {
                    if (navigationLevelsMap[2] == null) {
                        // Modify this in the future if we want to support a default UI.
//                        TwoPaneDefaultDetail(navigationLevelsMap[1]!!)
                        Box {
                        }
                    } else {
                        saveableStateHolder.SaveableStateProvider(navigationLevelsMap[2]!!.uri) {
                            ScreenPicker(navigationLevelsMap[2]!!)
                        }
                    }
                }
            }
        }
    )
}
