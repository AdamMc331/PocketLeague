package com.adammcneilly.pocketleague.shared

/**
 * When a screen is first navigated to, using "dkmpNav.navigate(screen, params), the following happen:
 * 1. The screen is initialized with the value defined in [initState].
 * 2. The FIRST recomposition is triggered, so that the [initState] is displayed.
 * 3. After recomposition, the [callOnInit] function is triggered.
 * 4. The [callOnInit] function typically calls [StateManager.updateScreen] to trigger another
 * recomposition.
 *
 * @property[title] The user friendly name of the screen.
 * @property[initState] A lambda used to generate the initial state of a screen.
 * @property[callOnInit] The lambda to be triggered when this screen is first added to composition.
 * @property[reInitOnEachNavigation] Defaults to false, but could be true if you want the screen
 * to update each time it returns to the front from somewhere in the backstack.
 * @property[callOnInitAlsoAfterBackground] Defaults to false, but can be true if we want to trigger
 * behavior when returning to foreground such as polling.
 */
class ScreenInitSettings(
    val title: String,
    val initState: (ScreenIdentifier) -> ScreenState,
    val callOnInit: suspend (StateManager) -> Unit,
    val reInitOnEachNavigation: Boolean = false,
    val callOnInitAlsoAfterBackground: Boolean = false,
)
