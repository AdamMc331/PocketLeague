package com.adammcneilly.pocketleague

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.view.WindowCompat
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.EventSummaryListDetailScreenDestination
import com.ramcosta.composedestinations.EventSummaryListScreenDestination
import dagger.hilt.android.AndroidEntryPoint

private const val TABLET_MIN_WIDTH_DP = 600

/**
 * The [MainActivity] is the user's main entry point into the application. This will be launched
 * when the application is first started.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            PocketLeagueTheme {
                SetSystemBarsTransparent()

                ProvideWindowInsets {
                    Scaffold { paddingValues ->
                        val isTablet =
                            LocalConfiguration.current.smallestScreenWidthDp >= TABLET_MIN_WIDTH_DP

                        val startDestination = if (isTablet) {
                            EventSummaryListDetailScreenDestination
                        } else {
                            EventSummaryListScreenDestination
                        }

                        DestinationsNavHost(
                            modifier = Modifier
                                .padding(paddingValues)
                                .statusBarsPadding()
                                .navigationBarsPadding(),
                            startDestination = startDestination,
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun SetSystemBarsTransparent() {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }
    }
}
