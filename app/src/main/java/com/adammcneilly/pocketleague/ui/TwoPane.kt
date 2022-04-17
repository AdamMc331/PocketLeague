package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenIdentifier

/**
 * A [TwoPane] layout is used to show two selections of content side by side. Something like this
 * is helpful on tablets or large screens to use up available screen space.
 */
@Suppress("MagicNumber")
@Composable
fun Navigation.TwoPane(
    saveableStateHolder: SaveableStateHolder
) {
    val navigationLevelsMap = getNavigationLevelsMap(currentLevel1ScreenIdentifier)!!
    Scaffold(
        topBar = { TopBar(getTitle(currentScreenIdentifier)) },
        content = {
            Row {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .width(80.dp)
                ) {
                    Level1NavigationRail(selectedTab = navigationLevelsMap[1]!!)
                }
                Column(
                    Modifier
                        .weight(0.4f)
                ) {
                    saveableStateHolder.SaveableStateProvider(navigationLevelsMap[1]!!.uri) {
                        ScreenPicker(navigationLevelsMap[1]!!)
                    }
                }
                Column(
                    Modifier
                        .weight(0.6f)
                        .padding(20.dp)
                ) {
                    if (navigationLevelsMap[2] == null) {
                        TwoPaneDefaultDetail(navigationLevelsMap[1]!!)
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

/**
 * For a given [screenIdentifier], create a default UI for the detail pane of a two pane layout.
 * If there's no default data to supply, we can just use a Box.
 */
@Composable
fun Navigation.TwoPaneDefaultDetail(
    screenIdentifier: ScreenIdentifier
) {
//    when (screenIdentifier.screen) {
//
//        CountriesList ->
//            CountriesListTwoPaneDefaultDetail()
//
//        else -> Box{}
//    }
    Box {
    }
}
