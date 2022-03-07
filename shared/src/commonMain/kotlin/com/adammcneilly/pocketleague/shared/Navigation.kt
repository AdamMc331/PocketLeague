package com.adammcneilly.pocketleague.shared

/**
 * Class that manages all of the navigation for our application.
 *
 * Uses the [stateManager] to track the active screens and backstacks.
 */
class Navigation(
    private val stateManager: StateManager,
) {

    init {
        var startScreenIdentifier = NavigationSettings.homeScreen.screenIdentifier
        if (NavigationSettings.saveLastLevel1Screen) {
            val savedUri = ScreenIdentifier.getByURI(dataRepository.localSettings.savedLevel1URI)
            startScreenIdentifier = savedUri ?: startScreenIdentifier
        }
        navigateByScreenIdentifier(startScreenIdentifier)
    }

    val stateProvider by lazy { StateProvider(stateManager) }
    val events by lazy { Events(stateManager) }

    /**
     * Retrieve the user friendly title for the [screenIdentifier].
     */
    fun getTitle(screenIdentifier: ScreenIdentifier): String {
        val screenInitSettings = screenIdentifier.getScreenInitSettings(this)
        return screenInitSettings.title
    }

    private val dataRepository
        get() = stateManager.dataRepository

    private val currentScreenIdentifier: ScreenIdentifier
        get() = stateManager.currentScreenIdentifier

    val currentLevel1ScreenIdentifier: ScreenIdentifier
        get() = stateManager.currentLevel1ScreenIdentifier

    val only1ScreenInBackstack: Boolean
        get() = stateManager.only1ScreenInBackstack

    // used by the Router composable in Compose apps
    // it returns a list of screens whose state has been removed, so they should also be removed from Compose's SaveableStateHolder
    /**
     * Used by the Router composable in our Compose apps.
     *
     * It returns a list of screens whose state has been removed, so they should also be removed
     * from Compose's SaveableStateHolder.
     */
    val screenStatesToRemove: List<ScreenIdentifier>
        get() = stateManager.getScreenStatesToRemove()

    /**
     * Used by the Router view in SwiftUI apps.
     *
     * It returns the list of Level1 screens to be rendered inside SwiftUI's ZStack.
     */
    val level1ScreenIdentifiers: List<ScreenIdentifier>
        get() = stateManager.getLevel1ScreenIdentifiers()

    private fun getNavigationLevelsMap(level1ScreenIdentifier: ScreenIdentifier): Map<Int, ScreenIdentifier>? {
        return stateManager.verticalNavigationLevels[level1ScreenIdentifier.uri]
    }

    /**
     * Determines if the screen matching the given [screenIdentifier] is in the current vertical
     * backstack visible to the user.
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
     * Navigates from our current position to the given [screen].
     */
    fun navigate(screen: Screens, params: ScreenParams? = null) {
        navigateByScreenIdentifier(ScreenIdentifier.get(screen, params))
    }

    /**
     * Navigates within our current level 1 navigation menu to the [level1NavigationItem].
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

    private fun navigateByScreenIdentifier(screenIdentifier: ScreenIdentifier) {
        debugLogger.log("navigate to /" + screenIdentifier.uri)
        val screenInitSettings = screenIdentifier.getScreenInitSettings(this)
        stateManager.addScreen(screenIdentifier, screenInitSettings)
        if (NavigationSettings.saveLastLevel1Screen && screenIdentifier.screen.navigationLevel == 1) {
            dataRepository.localSettings.savedLevel1URI = screenIdentifier.uri
        }
    }

    /**
     * Leave the screen matching the [screenIdentifier].
     */
    fun exitScreen(
        screenIdentifier: ScreenIdentifier? = null,
        triggerRecomposition: Boolean = true
    ) {
        val sID = screenIdentifier ?: currentScreenIdentifier
        debugLogger.log("exitScreen: " + sID.uri)
        stateManager.removeScreen(sID)
        if (triggerRecomposition) {
            navigateByScreenIdentifier(currentScreenIdentifier)
        }
    }

    /**
     * Called when our application is foregrounded, to determine if anything needs to be recomposed.
     */
    fun onReEnterForeground() {
        // not called at app startup, but only when reentering the app after it was in background
        debugLogger.log("onReEnterForeground: recomposition is triggered")
        val reinitializedScreens = stateManager.reInitScreenScopes()
        stateManager.triggerRecomposition()
        reinitializedScreens.forEach {
            it.getScreenInitSettings(this).apply {
                if (callOnInitAlsoAfterBackground) {
                    stateManager.runInScreenScope { callOnInit(stateManager) }
                }
            }
        }
    }

    /**
     * Notifies our app that we've been backgrounded, and cancels any necessary scopes.
     */
    fun onEnterBackground() {
        debugLogger.log("onEnterBackground: screen scopes are cancelled")
        stateManager.cancelScreenScopes()
    }

    /**
     * Triggered when our application changes orientation, we can recompose if necessary.
     */
    fun onChangeOrientation() {
        debugLogger.log("onChangeOrientation: recomposition is triggered")
        stateManager.triggerRecomposition()
    }
}
