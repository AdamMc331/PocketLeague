package com.adammcneilly.pocketleague.feature.core

/**
 * When a screen is initialized, the following flow occurs.
 *
 * First, the screen state is initialized with the value defined in [initState] (usually a loading UI).
 * Second, the FIRST recomposition will be triggered, so that the UI layer displays the [initState].
 * Third, after this recomposition, the [callOnInit] function is invoked. This will typically load data.
 * Fourth, the [callOnInit] function likely makes a call to the [StateManager] to update the screen, which will update
 * and trigger the SECOND recomposition.
 *
 * @property[title] The user friendly description of this screen.
 * @property[initState] A function used to create the initial [ScreenState].
 * @property[callOnInit] A function invoked when this screen is initialized.
 * @property[reInitOnEachNavigation] If true, the [callOnInit] function is invoked each time the
 * user returns to this screen.
 * @property[callOnInitAlsoAfterBackground] If true, the [callOnInit] function will be invoked if
 * we return to this screen from the background. This is useful if our screen was doing some polling,
 * for example.
 */
class ScreenInitSettings(
    val title: String,
    val initState: (ScreenIdentifier) -> ScreenState,
    // ARM - We may need to pass the state manager into this callback, but we're removing it for now
    // so this can continue to compile.
    val callOnInit: suspend () -> Unit,
    val reInitOnEachNavigation: Boolean = false,
    val callOnInitAlsoAfterBackground: Boolean = false,
)
