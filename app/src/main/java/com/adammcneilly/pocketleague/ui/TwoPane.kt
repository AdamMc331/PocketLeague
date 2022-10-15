package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenIdentifier
import com.adammcneilly.pocketleague.android.designsystem.components.bars.TopBar

private const val LIST_PANE_WIDTH_RATIO = 0.4F
private const val DETAIL_PANE_WIDTH_RATIO = 0.6F

/**
 * A [TwoPane] layout is used to show two selections of content side by side. Something like this
 * is helpful on tablets or large screens to use up available screen space.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation.TwoPane(
    saveableStateHolder: SaveableStateHolder,
) {
    val navigationLevelsMap = getNavigationLevelsMap(currentLevel1ScreenIdentifier)!!
    Scaffold(
        topBar = {
            TopBar(
                title = getTitle(currentScreenIdentifier),
                modifier = Modifier.statusBarsPadding(),
            )
        },
        content = { paddingValues ->
            Row(
                modifier = Modifier.padding(paddingValues)
            ) {
//                Column(
//                    Modifier
//                        .fillMaxHeight()
//                        .width(80.dp)
//                ) {
//                    Level1NavigationRail(selectedTab = navigationLevelsMap[1]!!)
//                }
                Column(
                    Modifier
                        .weight(LIST_PANE_WIDTH_RATIO)
                ) {
                    saveableStateHolder.SaveableStateProvider(navigationLevelsMap[1]!!.uri) {
                        ScreenPicker(navigationLevelsMap[1]!!)
                    }
                }
                Column(
                    Modifier
                        .weight(DETAIL_PANE_WIDTH_RATIO)
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
    screenIdentifier: ScreenIdentifier,
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
