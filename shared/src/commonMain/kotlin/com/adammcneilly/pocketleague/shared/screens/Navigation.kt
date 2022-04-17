package com.adammcneilly.pocketleague.shared.screens

/**
 * This class manages all of the navigation logic within the pocket league app.
 */
class Navigation(
    private val stateManager: StateManager,
) {
    /**
     * Creates a [StateProvider] allowing apps to get the state of necessary
     * screens during navigation.
     */
    val stateProvider by lazy {
        StateProvider(stateManager)
    }

    /**
     * Creates an [Events] instance which we can use to trigger actions and state
     * updates for screen.
     */
    val events by lazy {
        Events(stateManager)
    }

    init {
        val startScreenIdentifier = NavigationSettings.homeScreen.screenIdentifier
        navigateByScreenIdentifier(startScreenIdentifier)
    }

    /**
     * Returns the user friendly title for the given [screenIdentifier].
     */
    fun getTitle(screenIdentifier: ScreenIdentifier): String {
        val screenInitSettings = screenIdentifier.getScreenInitSettings(this)
        return screenInitSettings.title
    }

    /**
     * Pulls the [ScreenIdentifier] for the current screen from our [stateManager].
     */
    val currentScreenIdentifier: ScreenIdentifier
        get() = stateManager.currentScreenIdentifier

    /**
     * Gets the [ScreenIdentifier] of the currently selected tab from our [stateManager].
     */
    val currentLevel1ScreenIdentifier: ScreenIdentifier
        get() = stateManager.currentLevel1ScreenIdentifier

    /**
     * Determines if there is only 1 screen within our backstacks.
     */
    val only1ScreenInBackstack: Boolean
        get() = stateManager.only1ScreenInBackstack

    /**
     * This is used by the router in compose apps, so we can remove screens from compose's
     * SaveableStateHolder.
     */
    val screenStatesToRemove: List<ScreenIdentifier>
        get() = stateManager.getScreenStatesToRemove()

    /**
     * This is used by SwiftUI apps to get the list of level1 screens to add to a SwiftUI's
     * ZStack.
     */
    val level1ScreenIdentifiers: List<ScreenIdentifier>
        get() = stateManager.getLevel1ScreenIdentifiers()

    /**
     * Gets the vertical navigation levels for a given [level1ScreenIdentifier].
     */
    fun getNavigationLevelsMap(level1ScreenIdentifier: ScreenIdentifier): Map<Int, ScreenIdentifier>? {
        return stateManager.verticalNavigationLevels[level1ScreenIdentifier.uri]
    }

    /**
     * Determines if the given [screenIdentifier] is inside our current vertical stack.
     */
    fun isInCurrentVerticalBackstack(screenIdentifier: ScreenIdentifier): Boolean {
        stateManager.currentVerticalBackstack.forEach {
            if (it.uri == screenIdentifier.uri) {
                return true
            }
        }

        return false
    }

    /**
     * Navigates to a given [screen] with the provides [params].
     */
    fun navigate(screen: Screens, params: ScreenParams? = null) {
        navigateByScreenIdentifier(ScreenIdentifier.get(screen, params))
    }

    /**
     * Navigate to a different level 1 menu item.
     */
    fun navigateByLevel1Menu(level1NavigationItem: Level1Navigation) {
        val navigationLevelsMap = getNavigationLevelsMap(level1NavigationItem.screenIdentifier)

        if (navigationLevelsMap == null) {
            navigateByScreenIdentifier(level1NavigationItem.screenIdentifier)
        } else {
            navigationLevelsMap.keys.sorted().forEach {
                navigateByScreenIdentifier(navigationLevelsMap[it]!!)
            }
        }
    }

    /**
     * Navigate to a screen using the given [screenIdentifier].
     */
    fun navigateByScreenIdentifier(screenIdentifier: ScreenIdentifier) {
        val screenInitSettings = screenIdentifier.getScreenInitSettings(this)
        stateManager.addScreen(screenIdentifier, screenInitSettings)

        if (NavigationSettings.saveLastLevel1Screen && screenIdentifier.screen.navigationLevel == 1) {
            // Saving screen coming soon
        }
    }

    /**
     * Exit the screen with the given [screenIdentifier] or the current screen if this is null.
     */
    fun exitScreen(screenIdentifier: ScreenIdentifier? = null, triggerRecomposition: Boolean = true) {
        val screenId = screenIdentifier ?: currentScreenIdentifier

        stateManager.removeScreen(screenId)

        if (triggerRecomposition) {
            navigateByScreenIdentifier(currentScreenIdentifier)
        }
    }

    /**
     * This is not called at app startup, but only when reentering the app after it was in the background.
     */
    fun onReEnterForeground() {
        val reInitializedScreens = stateManager.reInitScreenScopes()
        stateManager.triggerRecomposition()
        reInitializedScreens.forEach {
            it.getScreenInitSettings(this).apply {
                if (callOnInitAlsoAfterBackground) {
                    stateManager.runInScreenScope { callOnInit(stateManager) }
                }
            }
        }
    }

    /**
     * Cancel any screen scopes when we enter the background.
     */
    fun onEnterBackground() {
        stateManager.cancelScreenScopes()
    }

    /**
     * If the user changes device orientation we should recompose our application.
     */
    fun onChangeOrientation() {
        stateManager.triggerRecomposition()
    }
}
