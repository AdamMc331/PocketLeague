package com.adammcneilly.pocketleague.shared.ui.scaffold

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.adammcneilly.pocketleague.shared.ui.currentWindowWidthSizeClass

/**
 * Do not create an instance of this scaffold state directly. Please use
 * [rememberScaffoldState].
 */
@OptIn(ExperimentalSharedTransitionApi::class)
class ScaffoldState internal constructor(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    private val isMediumScreenWidthOrWider: State<Boolean>,
) : AnimatedVisibilityScope by animatedVisibilityScope,
    SharedTransitionScope by sharedTransitionScope {
    val canShowBottomNavigation get() = !isMediumScreenWidthOrWider.value

    val canShowNavRail get() = isMediumScreenWidthOrWider.value
}

/**
 * Remembers an instance of [ScaffoldState] with the supplied animation scopes.
 *
 * This should be used instead of the constructor for [ScaffoldState].
 */
@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun rememberScaffoldState(
    animatedVisibilityScope: AnimatedVisibilityScope = requireNotNull(LocalNavAnimatedVisibilityScope.current) {
        "AnimatedVisibilityScope must be provided via LocalNavAnimatedVisibilityScope"
    },
    sharedTransitionScope: SharedTransitionScope = requireNotNull(LocalSharedTransitionScope.current) {
        "SharedTransitionScope must be provided via LocalSharedTransitionScope"
    },
): ScaffoldState {
    val isMediumScreenWidthOrWider = isMediumScreenWidthOrWider()

    return remember {
        ScaffoldState(
            animatedVisibilityScope = animatedVisibilityScope,
            sharedTransitionScope = sharedTransitionScope,
            isMediumScreenWidthOrWider = isMediumScreenWidthOrWider,
        )
    }
}

/**
 * Access the current [WindowWidthSizeClass] to determine if it's at
 * a medium or wider width class. This is used to determine whether
 * we should show a bottom navigation or a navigation rail.
 */
@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
private fun isMediumScreenWidthOrWider(): State<Boolean> {
    val widthSizeClass = currentWindowWidthSizeClass()

    val mediumOrHigherClasses = listOf(
        WindowWidthSizeClass.Medium,
        WindowWidthSizeClass.Expanded,
    )

    val isMediumScreenWidthOrWider = (widthSizeClass in mediumOrHigherClasses)

    return rememberUpdatedState(isMediumScreenWidthOrWider)
}
