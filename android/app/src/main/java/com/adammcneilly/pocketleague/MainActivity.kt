package com.adammcneilly.pocketleague

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.ui.MainComposable
import com.adammcneilly.pocketleague.ui.sizeconfigs.ContentType
import com.adammcneilly.pocketleague.ui.sizeconfigs.NavigationType
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * The [MainActivity] is the user's main entry point into the application. This will be launched
 * when the application is first started.
 */
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val (navigationType, contentType) = getNavigationAndContentTypes(
                windowSizeClass = calculateWindowSizeClass(activity = this),
            )

            PocketLeagueTheme {
                SetSystemBarsTransparent()

                val viewModel = (this.application as PocketLeagueApp).viewModel
                MainComposable(
                    viewModel = viewModel,
                    navigationType = navigationType,
                    contentType = contentType,
                )
            }
        }
    }

    @Composable
    private fun getNavigationAndContentTypes(
        windowSizeClass: WindowSizeClass,
    ): Pair<NavigationType, ContentType> {
        val navigationType: NavigationType
        val contentType: ContentType

        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Medium -> {
                navigationType = NavigationType.NAVIGATION_RAIL
                contentType = ContentType.SINGLE_PANE
            }

            WindowWidthSizeClass.Expanded -> {
                navigationType = NavigationType.NAVIGATION_RAIL
                contentType = ContentType.DUAL_PANE
            }

            else -> {
                navigationType = NavigationType.BOTTOM_NAVIGATION
                contentType = ContentType.SINGLE_PANE
            }
        }

        return (navigationType to contentType)
    }

    @Composable
    private fun SetSystemBarsTransparent() {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons,
            )
        }
    }
}
