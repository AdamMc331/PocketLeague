package com.adammcneilly.pocketleague.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

/**
 * An empty interface used to provide type safety for any state class to define the UI of a screen.
 */
interface ScreenState

/**
 * An empty interface used to provide type safety for parameters that should be applied to a screen.
 */
interface ScreenParams

/**
 * State management container for the entire application.
 *
 * @param[repository] Reference to the data layer.
 */
@Suppress("TooManyFunctions")
class StateManager(
    repository: Repository,
) {

    internal val mutableStateFlow = MutableStateFlow(AppState())

    /**
     * Collection of screen states currently in memory.
     */
    val screenStatesMap: MutableMap<URI, ScreenState> = mutableMapOf()

    /**
     * Map of scopes associated to the current [screenStatesMap].
     */
    private val screenScopesMap: MutableMap<URI, CoroutineScope> = mutableMapOf()

    /**
     * List of elements that are screen identifiers for our Level 1 Navigation.
     */
    private val level1Backstack: MutableList<ScreenIdentifier> = mutableListOf()

    /**
     * A map of back stacks for each level 1 screen. The key is the URI for the screen identifier
     * of the level 1 item.
     */
    private val verticalBackstacks: MutableMap<URI, MutableList<ScreenIdentifier>> = mutableMapOf()

    /**
     * The first map key is the URI identifier of the NavigationLevel1 screen.
     *
     * The second map key is the level number of the navigation.
     */
    val verticalNavigationLevels: MutableMap<URI, MutableMap<Int, ScreenIdentifier>> =
        mutableMapOf()

    private val lastRemovedScreens = mutableListOf<ScreenIdentifier>()

    internal val dataRepository by lazy { repository }

    val currentScreenIdentifier: ScreenIdentifier
        get() = currentVerticalBackstack.lastOrNull() ?: level1Backstack.last()

    val currentLevel1ScreenIdentifier: ScreenIdentifier
        get() = level1Backstack.last()

    val currentVerticalBackstack: MutableList<ScreenIdentifier>
        get() = verticalBackstacks[currentLevel1ScreenIdentifier.uri]!!

    val currentVerticalNavigationLevelsMap: MutableMap<Int, ScreenIdentifier>
        get() = verticalNavigationLevels[currentLevel1ScreenIdentifier.uri]!!

    val only1ScreenInBackstack: Boolean
        get() = level1Backstack.size + currentVerticalBackstack.size == 2

    /**
     * This is used by Compose apps.
     *
     * Adam to figure out when/why this is called.
     */
    fun getScreenStatesToRemove(): List<ScreenIdentifier> {
        val screenStatesToRemove = lastRemovedScreens.toList()
        lastRemovedScreens.clear()
        return screenStatesToRemove
    }

    /**
     * This is used by Swift apps. Adam to figure out when/why this is called.
     */
    fun getLevel1ScreenIdentifiers(): List<ScreenIdentifier> {
        val screenIdentifiers = verticalNavigationLevels.values.map { it[1]!! }.toMutableList()

        // Remove any that don't have a stored state.
        screenIdentifiers.removeAll { !screenStatesMap.containsKey(it.uri) }

        return screenIdentifiers
    }

    private fun isInTheStatesMap(screenIdentifier: ScreenIdentifier): Boolean {
        return screenStatesMap.containsKey(screenIdentifier.uri)
    }

    private fun isInAnyVerticalBackstack(screenIdentifier: ScreenIdentifier): Boolean {
        return verticalBackstacks.any { verticalBackstack ->
            verticalBackstack.value.any {
                it.uri == screenIdentifier.uri
            }
        }
    }

    /**
     * Performs an update to a screen via the [update] block.
     */
    inline fun <reified T : ScreenState> updateScreen(
        stateClass: KClass<T>,
        update: (T) -> T,
    ) {
        // debugLogger.log("updateScreen: "+currentScreenIdentifier.URI)

        lateinit var screenIdentifier: ScreenIdentifier
        var screenState: T? = null
        for (i in currentVerticalNavigationLevelsMap.keys.sortedDescending()) {
            screenState = screenStatesMap[currentVerticalNavigationLevelsMap[i]?.uri] as? T
            if (screenState != null) {
                screenIdentifier = currentVerticalNavigationLevelsMap[i]!!
                break
            }
        }

        // Only perform screen state update if screen is currently visible.
        if (screenState != null) {
            screenStatesMap[screenIdentifier.uri] = update(screenState)
            triggerRecomposition()
            debugLogger.log("state updated @ /${screenIdentifier.uri}: recomposition is triggered")
        }
    }

    /**
     * Force a recomposition of the application by modifying the [AppState.recompositionIndex]
     * property.
     */
    fun triggerRecomposition() {
        mutableStateFlow.value = AppState(mutableStateFlow.value.recompositionIndex + 1)
    }

    /**
     * Add a screen to our backstack.
     */
    fun addScreen(screenIdentifier: ScreenIdentifier, screenInitSettings: ScreenInitSettings) {
        // debugLogger.log("addScreen: "+screenIdentifier.URI)
        addScreenToBackstack(screenIdentifier)
        initScreenScope(screenIdentifier)
        if (!isInTheStatesMap(screenIdentifier) || screenInitSettings.reInitOnEachNavigation) {
            screenStatesMap[screenIdentifier.uri] = screenInitSettings.initState(screenIdentifier)
            // First recomposition
            triggerRecomposition()
            runInScreenScope(screenIdentifier) {
                // Second recomposition
                screenInitSettings.callOnInit(this)
            }
        } else {
            // Just 1 recomposition
            triggerRecomposition()
        }
        // debugLogger.log("currentVerticalBackstack: "+currentVerticalBackstack.map{it.URI}.toString())
        // debugLogger.log("currentVerticalNavigationLevelsMap: "+currentVerticalNavigationLevelsMap.values.map{it.URI}.toString())
        // debugLogger.log("level1Backstack: "+level1Backstack.map{it.URI}.toString())
        // debugLogger.log("screenScopesMap: "+screenScopesMap.keys.map{it}.toString())
        // debugLogger.log("screenStatesMap: "+screenStatesMap.keys.map{it}.toString())
    }

    private fun addScreenToBackstack(screenIdentifier: ScreenIdentifier) {
        if (screenIdentifier.screen.navigationLevel == 1) {
            if (level1Backstack.size > 0) {
                val sameAsNewScreen =
                    screenIdentifier.screen == currentLevel1ScreenIdentifier.screen
                clearLevel1Screen(currentLevel1ScreenIdentifier, sameAsNewScreen)
            }
            setupNewLevel1Screen(screenIdentifier)
        } else {
            if (currentScreenIdentifier.uri == screenIdentifier.uri) {
                return
            }
            if (
                currentScreenIdentifier.screen == screenIdentifier.screen &&
                !screenIdentifier.screen.stackableInstances
            ) {
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
        currentVerticalNavigationLevelsMap[screenIdentifier.screen.navigationLevel] =
            screenIdentifier
    }

    /**
     * Remove a screen from the relevant backstacks.
     */
    fun removeScreen(screenIdentifier: ScreenIdentifier) {
        if (screenIdentifier.screen.navigationLevel == 1) {
            level1Backstack.remove(screenIdentifier)
            removeScreenStateAndScope(screenIdentifier)
        } else {
            currentVerticalNavigationLevelsMap.remove(screenIdentifier.screen.navigationLevel)
            currentVerticalBackstack.removeAll { it.uri == screenIdentifier.uri }
            currentVerticalNavigationLevelsMap[currentScreenIdentifier.screen.navigationLevel] =
                currentScreenIdentifier // set new currentScreenIdentifier, after the removal
            if (!isInAnyVerticalBackstack(screenIdentifier)) {
                removeScreenStateAndScope(screenIdentifier)
            }
        }
    }

    private fun removeScreenStateAndScope(screenIdentifier: ScreenIdentifier) {
        debugLogger.log("removeScreenStateAndScope /" + screenIdentifier.uri)
        screenScopesMap[screenIdentifier.uri]?.cancel() // cancel screen's coroutine scope
        screenScopesMap.remove(screenIdentifier.uri)
        screenStatesMap.remove(screenIdentifier.uri)
        lastRemovedScreens.add(screenIdentifier)
    }

    private fun clearLevel1Screen(screenIdentifier: ScreenIdentifier, sameAsNewScreen: Boolean) {
        // debugLogger.log("clear vertical backstack /"+screenIdentifier.URI)
        if (!screenIdentifier.level1VerticalBackstackEnabled()) {
            currentVerticalBackstack.forEach {
                if (it.screen.navigationLevel > 1) {
                    removeScreenStateAndScope(it)
                }
            }
            currentVerticalBackstack.removeAll { it.uri != screenIdentifier.uri }
            currentVerticalNavigationLevelsMap.keys.removeAll { it != 1 }
        }
        if (sameAsNewScreen && !screenIdentifier.screen.stackableInstances) {
            removeScreenStateAndScope(screenIdentifier)
            currentVerticalBackstack.clear()
            currentVerticalNavigationLevelsMap.clear()
            level1Backstack.remove(screenIdentifier)
        }
    }

    private fun setupNewLevel1Screen(screenIdentifier: ScreenIdentifier) {
        level1Backstack.removeAll { it.uri == screenIdentifier.uri }
        if (NavigationSettings.alwaysQuitOnHomeScreen) {
            if (screenIdentifier.uri == NavigationSettings.homeScreen.screenIdentifier.uri) {
                level1Backstack.clear() // remove all elements
            } else if (level1Backstack.size == 0) {
                addLevel1ScreenToBackstack(NavigationSettings.homeScreen.screenIdentifier)
            }
        }
        addLevel1ScreenToBackstack(screenIdentifier)
    }

    private fun addLevel1ScreenToBackstack(screenIdentifier: ScreenIdentifier) {
        level1Backstack.add(screenIdentifier)
        if (verticalBackstacks[screenIdentifier.uri] == null) {
            verticalBackstacks[screenIdentifier.uri] = mutableListOf(screenIdentifier)
            verticalNavigationLevels[screenIdentifier.uri] = mutableMapOf(1 to screenIdentifier)
        }
    }

    private fun initScreenScope(screenIdentifier: ScreenIdentifier) {
        // debugLogger.log("initScreenScope()")
        screenScopesMap[screenIdentifier.uri]?.cancel()
        screenScopesMap[screenIdentifier.uri] = CoroutineScope(Job() + Dispatchers.Main)
    }

    /**
     * Reinitialize the coroutine scopes for screens.
     */
    fun reInitScreenScopes(): List<ScreenIdentifier> {
        // debugLogger.log("reinitScreenScopes()")
        currentVerticalNavigationLevelsMap.forEach {
            screenScopesMap[it.value.uri] = CoroutineScope(Job() + Dispatchers.Main)
        }

        // Return list of screens whose scope has been reinitialized
        return currentVerticalNavigationLevelsMap.values.toMutableList()
    }

    /**
     * Run some [block] inside the coroutine scope for the given [screenIdentifier].
     */
    fun runInScreenScope(screenIdentifier: ScreenIdentifier? = null, block: suspend () -> Unit) {
        val uri = screenIdentifier?.uri ?: currentScreenIdentifier.uri
        val screenScope = screenScopesMap[uri]
        screenScope?.launch {
            block()
        }
    }

    /**
     * Cancels all coroutine scopes for our screens.
     */
    fun cancelScreenScopes() {
        // debugLogger.log("cancelScreenScopes()")
        screenScopesMap.forEach {
            it.value.cancel() // cancel screen's coroutine scope
        }
    }
}

/**
 * Data class defining the state of our application.
 *
 * @property[recompositionIndex] Used to track how many times our app has been composed. We can
 * toggle this property to force a recomposition.
 */
data class AppState(
    val recompositionIndex: Int = 0,
) {
    /**
     * Return the [Navigation] instance for this app by pulling it from our [viewModel].
     */
    fun getNavigation(viewModel: DKMPViewModel): Navigation {
        return viewModel.navigation
    }
}
