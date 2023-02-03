package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.core.feature.ScreenState
import com.adammcneilly.pocketleague.shared.di.AppModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

/**
 * Manages our current [AppState] as well as the backstacks and screen scopes for everything in memory
 * at any given moment.
 */
@Suppress("TooManyFunctions")
class StateManager(
    val appModule: AppModule
) {
    internal val mutableStateFlow = MutableStateFlow(AppState())

    /**
     * A map of all screens and their current [ScreenState] in memory.
     */
    val screenStatesMap: MutableMap<String, ScreenState> = mutableMapOf()

    /**
     * A map of all screens and coroutine scopes associated with them in memory.
     */
    val screenScopesMap: MutableMap<String, CoroutineScope> = mutableMapOf()

    /**
     * The vertical backstack that the user has navigated to. These should all map to [Level1Navigation]
     * screen identifiers.
     */
    val level1Backstack: MutableList<ScreenIdentifier> = mutableListOf()

    /**
     * Tracks the vertical backstack for all tabs. The key is the identifier of the [Level1Navigation],
     * and the value is the backstack within that screen.
     */
    val verticalBackstacks: MutableMap<String, MutableList<ScreenIdentifier>> = mutableMapOf()

    /**
     * The key for this map is the [Level1Navigation] screen identifier, and the value is the current
     * navigation levels within that backstack.
     */
    val verticalNavigationLevels: MutableMap<String, MutableMap<Int, ScreenIdentifier>> = mutableMapOf()

    /**
     * Keeping track of the screens recently removed from memory. This will be used by the mobile apps
     * to make sure they are removed from composition.
     */
    val lastRemovedScreens: MutableList<ScreenIdentifier> = mutableListOf()

    /**
     * Checks our [level1Backstack] and uses whatever was most recently added to it.
     */
    val currentLevel1ScreenIdentifier: ScreenIdentifier
        get() = level1Backstack.last()

    /**
     * Pulls the [currentLevel1ScreenIdentifier] and gets the vertical backstack for that tab.
     */
    val currentVerticalBackstack: MutableList<ScreenIdentifier>
        get() = verticalBackstacks[currentLevel1ScreenIdentifier.uri]!!

    /**
     * Checks the current screen by looking at our [currentVerticalBackstack] or falling back on
     * the [currentLevel1ScreenIdentifier] if no vertical stack.
     */
    val currentScreenIdentifier: ScreenIdentifier
        get() = currentVerticalBackstack.lastOrNull() ?: currentLevel1ScreenIdentifier

    /**
     * Checks the [verticalNavigationLevels] for the backstack mapping to our [currentLevel1ScreenIdentifier]
     */
    val currentVerticalNavigationLevelsMap: MutableMap<Int, ScreenIdentifier>
        get() = verticalNavigationLevels[currentLevel1ScreenIdentifier.uri]!!

    /**
     * If there's only one screen in our [level1Backstack] as well as only one screen in the [currentVerticalBackstack]
     * then we know we only have one screen total.
     */
    val only1ScreenInBackstack: Boolean
        get() = level1Backstack.size + currentVerticalBackstack.size == 2

    /**
     * This is used by Compose apps to remove screens from memory.
     */
    fun getScreenStatesToRemove(): List<ScreenIdentifier> {
        val screenStatesToRemove = lastRemovedScreens.toList()
        lastRemovedScreens.clear()
        return screenStatesToRemove
    }

    /**
     * This is used by SwiftUI apps to build the level1 navigation.
     */
    fun getLevel1ScreenIdentifiers(): List<ScreenIdentifier> {
        val screenIdentifiers = verticalNavigationLevels.values.map {
            it[1]!!
        }.toMutableList()

        // Remove any screens that do not currently have state stored.
        screenIdentifiers.removeAll {
            !screenStatesMap.containsKey(it.uri)
        }

        return screenIdentifiers
    }

    /**
     * Checks to see if we've stored the screen state for a given [screenIdentifier].
     */
    fun isInTheStatesMap(screenIdentifier: ScreenIdentifier): Boolean {
        return screenStatesMap.containsKey(screenIdentifier.uri)
    }

    /**
     * Determines if the given [screenIdentifier] is in any of our vertical backstacks within
     * the application.
     */
    fun isInAnyVerticalBackstack(screenIdentifier: ScreenIdentifier): Boolean {
        verticalBackstacks.forEach { verticalBackstack ->
            verticalBackstack.value.forEach { screen ->
                if (screen.uri == screenIdentifier.uri) {
                    return true
                }
            }
        }

        return false
    }

    /**
     * Updates any screens with the matching [stateClass]. The update is calculated through the
     * given [update] lambda.
     */
    inline fun <reified T : ScreenState> updateScreen(
        stateClass: KClass<T>,
        update: (T) -> T
    ) {
        lateinit var screenIdentifier: ScreenIdentifier
        var screenState: T? = null

        for (index in currentVerticalNavigationLevelsMap.keys.sortedDescending()) {
            screenState = screenStatesMap[currentVerticalNavigationLevelsMap[index]?.uri] as? T

            if (screenState != null) {
                screenIdentifier = currentVerticalNavigationLevelsMap[index]!!
                break
            }
        }

        // This ensures we only perform a screen state update if the screen is currently
        // visible.
        if (screenState != null) {
            screenStatesMap[screenIdentifier.uri] = update(screenState)
            triggerRecomposition()
        }
    }

    /**
     * Update our [mutableStateFlow] with a new value so that we can force a recomposition
     * in our application.
     */
    fun triggerRecomposition() {
        mutableStateFlow.update {
            AppState(it.recompositionIndex + 1)
        }
    }

    /**
     * Adds this screen to the app and trigger compositions as necessary.
     */
    fun addScreen(screenIdentifier: ScreenIdentifier, screenInitSettings: ScreenInitSettings) {
        addScreenToBackstack(screenIdentifier)
        initScreenScope(screenIdentifier)

        if (!isInTheStatesMap(screenIdentifier) || screenInitSettings.reInitOnEachNavigation) {
            screenStatesMap[screenIdentifier.uri] = screenInitSettings.initState(screenIdentifier)
            // First UI Recomposition
            triggerRecomposition()

            runInScreenScope(screenIdentifier) {
                // Second UI Recomposition
                screenInitSettings.callOnInit(this)
            }
        } else {
            // Just One UI Recomposition
            triggerRecomposition()
        }
    }

    /**
     * Adds a screen to our backstack, based on its navigation level it will get added to the correct
     * one.
     */
    fun addScreenToBackstack(screenIdentifier: ScreenIdentifier) {
        if (screenIdentifier.screen.navigationLevel == 1) {
            if (level1Backstack.size > 0) {
                val sameAsNewScreen = screenIdentifier.screen == currentLevel1ScreenIdentifier.screen
                clearLevel1Screen(currentLevel1ScreenIdentifier, sameAsNewScreen)
            }

            setupNewLevel1Screen(screenIdentifier)
        } else {
            if (currentScreenIdentifier.uri == screenIdentifier.uri) {
                return
            }

            val isCurrentScreen = (currentScreenIdentifier.screen == screenIdentifier.screen)
            if (isCurrentScreen && !screenIdentifier.screen.stackableInstances) {
                val currentScreenId = currentScreenIdentifier
                currentVerticalNavigationLevelsMap.remove(currentScreenId.screen.navigationLevel)
                currentVerticalBackstack.remove(currentScreenId)

                if (!isInAnyVerticalBackstack(currentScreenId)) {
                    removeScreenStateAndScope(currentScreenId)
                }
            }

            if (currentVerticalBackstack.lastOrNull()?.uri != screenIdentifier.uri) {
                currentVerticalBackstack.add(screenIdentifier)
            }
        }

        currentVerticalNavigationLevelsMap[screenIdentifier.screen.navigationLevel] = screenIdentifier
    }

    /**
     * Remove this screen from memory.
     */
    fun removeScreen(screenIdentifier: ScreenIdentifier) {
        if (screenIdentifier.screen.navigationLevel == 1) {
            level1Backstack.remove(screenIdentifier)
            removeScreenStateAndScope(screenIdentifier)
        } else {
            currentVerticalNavigationLevelsMap.remove(screenIdentifier.screen.navigationLevel)
            currentVerticalBackstack.removeAll {
                it.uri == screenIdentifier.uri
            }
            currentVerticalNavigationLevelsMap[currentScreenIdentifier.screen.navigationLevel] = currentScreenIdentifier

            if (!isInAnyVerticalBackstack(screenIdentifier)) {
                removeScreenStateAndScope(screenIdentifier)
            }
        }
    }

    /**
     * Remove the in memory state and coroutine scope for the given screen.
     */
    fun removeScreenStateAndScope(screenIdentifier: ScreenIdentifier) {
        screenScopesMap[screenIdentifier.uri]?.cancel()
        screenScopesMap.remove(screenIdentifier.uri)
        screenStatesMap.remove(screenIdentifier.uri)
        lastRemovedScreens.add(screenIdentifier)
    }

    /**
     * Clear a level 1 screen's backstack and remove from [level1Backstack] if necessary.
     */
    fun clearLevel1Screen(screenIdentifier: ScreenIdentifier, sameAsNewScreen: Boolean) {
        if (!screenIdentifier.level1VerticalBackstackEnabled()) {
            currentVerticalBackstack.forEach {
                if (it.screen.navigationLevel > 1) {
                    removeScreenStateAndScope(it)
                }
            }

            currentVerticalBackstack.removeAll {
                it.uri != screenIdentifier.uri
            }
            currentVerticalNavigationLevelsMap.keys.removeAll {
                it != 1
            }
        }

        if (sameAsNewScreen && !screenIdentifier.screen.stackableInstances) {
            removeScreenStateAndScope(screenIdentifier)
            currentVerticalBackstack.clear()
            currentVerticalNavigationLevelsMap.clear()
            level1Backstack.remove(screenIdentifier)
        }
    }

    /**
     * Setup a new level 1 screen when it's visited for the first time.
     */
    fun setupNewLevel1Screen(screenIdentifier: ScreenIdentifier) {
        level1Backstack.removeAll {
            it.uri == screenIdentifier.uri
        }

        if (NavigationSettings.alwaysQuitOnHomeScreen) {
            if (screenIdentifier.uri == NavigationSettings.homeScreen.screenIdentifier.uri) {
                // Remove all elements
                level1Backstack.clear()
            } else if (level1Backstack.size == 0) {
                addLevel1ScreenToBackstack(NavigationSettings.homeScreen.screenIdentifier)
            }
        }

        addLevel1ScreenToBackstack(screenIdentifier)
    }

    /**
     * Adds a level 1 screen to our backstack, will clear the vertical backstack for this screen
     * if one was there.
     */
    private fun addLevel1ScreenToBackstack(screenIdentifier: ScreenIdentifier) {
        level1Backstack.add(screenIdentifier)

        if (verticalBackstacks[screenIdentifier.uri] == null) {
            verticalBackstacks[screenIdentifier.uri] = mutableListOf(screenIdentifier)
            verticalNavigationLevels[screenIdentifier.uri] = mutableMapOf(1 to screenIdentifier)
        }
    }

    /**
     * Initializes a coroutine scope for a given screen.
     */
    fun initScreenScope(screenIdentifier: ScreenIdentifier) {
        screenScopesMap[screenIdentifier.uri]?.cancel()
        screenScopesMap[screenIdentifier.uri] = CoroutineScope(Job() + Dispatchers.Main)
    }

    /**
     * Reinitializes all scopes for our current vertical navigation screens.
     */
    fun reInitScreenScopes(): List<ScreenIdentifier> {
        currentVerticalNavigationLevelsMap.forEach {
            screenScopesMap[it.value.uri] = CoroutineScope(Job() + Dispatchers.Main)
        }

        return currentVerticalNavigationLevelsMap.values.toList()
    }

    /**
     * Runs the supplied [block] within the coroutine scope of the given [screenIdentifier],
     * or the current screen if one not passed.
     */
    fun runInScreenScope(screenIdentifier: ScreenIdentifier? = null, block: suspend (CoroutineScope) -> Unit) {
        val screenScope = getScreenScope(screenIdentifier)

        screenScope?.launch {
            block(screenScope)
        }
    }

    /**
     * Retrieves the [CoroutineScope] for the supplied [screenIdentifier] or current screen
     * as default.
     */
    fun getScreenScope(screenIdentifier: ScreenIdentifier? = null): CoroutineScope? {
        val uri = screenIdentifier?.uri ?: currentScreenIdentifier.uri

        return screenScopesMap[uri]
    }

    /**
     * Cancel all of our coroutine scopes.
     */
    fun cancelScreenScopes() {
        screenScopesMap.forEach {
            it.value.cancel()
        }
    }
}
