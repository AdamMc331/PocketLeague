package com.adammcneilly.pocketleague.shared.ui.scaffold.navigation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.ui.scaffold.ScaffoldState

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun ScaffoldState.PersistentNavigationRail(
    modifier: Modifier = Modifier,
    enterTransition: EnterTransition = slideInVertically(initialOffsetY = { it }),
    exitTransition: ExitTransition = slideOutVertically(targetOffsetY = { it }),
) {
    AnimatedVisibility(
        modifier = modifier
            .sharedElement(
                sharedContentState = rememberSharedContentState(
                    NavRailSharedElementKey,
                ),
                animatedVisibilityScope = this,
            ),
        visible = canShowNavRail,
        enter = enterTransition,
        exit = exitTransition,
        content = {
            SideNavigationRail()
        },
    )
}

private data object NavRailSharedElementKey
