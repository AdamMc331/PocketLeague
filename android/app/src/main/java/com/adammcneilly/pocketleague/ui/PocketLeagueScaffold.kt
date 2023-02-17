package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.components.bars.TopBar
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.ui.composables.bars.Level1BottomBar
import com.adammcneilly.pocketleague.ui.composables.bars.Level1NavigationRail
import com.adammcneilly.pocketleague.ui.sizeconfigs.ContentType
import com.adammcneilly.pocketleague.ui.sizeconfigs.NavigationType

private const val LIST_PANE_WIDTH_RATIO = 0.4F
private const val DETAIL_PANE_WIDTH_RATIO = 0.6F

/**
 * This this scaffold for the main Pocket League content
 * that will set it's navigation and content configfurations
 * based on the supplied [navigationType] and [contentType].
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("LongMethod")
fun Navigation.PocketLeagueScaffold(
    navigationType: NavigationType,
    contentType: ContentType,
    saveableStateHolder: SaveableStateHolder,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = getTitle(currentScreenIdentifier),
                modifier = Modifier.statusBarsPadding(),
            )
        },
        bottomBar = {
            val isLevelOneNavigation = (currentScreenIdentifier.screen.navigationLevel == 1)
            val isBottomNavType = (navigationType == NavigationType.BOTTOM_NAVIGATION)

            if (isBottomNavType && isLevelOneNavigation) {
                Level1BottomBar(
                    selectedTab = currentScreenIdentifier,
                    modifier = Modifier
                        .navigationBarsPadding(),
                )
            }
        },
    ) { paddingValues ->
        Row(
            modifier = Modifier.padding(paddingValues),
        ) {
            val navigationLevelsMap = getNavigationLevelsMap(currentLevel1ScreenIdentifier)!!

            if (navigationType == NavigationType.NAVIGATION_RAIL) {
                Level1NavigationRail(
                    selectedTab = navigationLevelsMap[1]!!,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(80.dp),
                )
            }

            when (contentType) {
                ContentType.SINGLE_PANE -> {
                    SinglePaneContent(
                        saveableStateHolder = saveableStateHolder,
                    )
                }
                ContentType.DUAL_PANE -> {
                    Column(
                        Modifier
                            .weight(LIST_PANE_WIDTH_RATIO),
                    ) {
                        saveableStateHolder.SaveableStateProvider(navigationLevelsMap[1]!!.uri) {
                            ScreenPicker(navigationLevelsMap[1]!!)
                        }
                    }

                    Column(
                        Modifier
                            .weight(DETAIL_PANE_WIDTH_RATIO)
                            .padding(20.dp),
                    ) {
                        if (navigationLevelsMap[2] == null) {
                            DualPaneDefaultDetail(navigationLevelsMap[1]!!)
                        } else {
                            saveableStateHolder.SaveableStateProvider(navigationLevelsMap[2]!!.uri) {
                                ScreenPicker(navigationLevelsMap[2]!!)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Navigation.SinglePaneContent(
    saveableStateHolder: SaveableStateHolder,
) {
    saveableStateHolder.SaveableStateProvider(currentScreenIdentifier.uri) {
        ScreenPicker(
            currentScreenIdentifier,
            paddingValues = PaddingValues(0.dp),
        )
    }
}
