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
import androidx.core.view.WindowCompat
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.teamlist.ui.TeamListScreen
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

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
                        TeamListScreen(
                            modifier = Modifier
                                .padding(paddingValues)
                                .statusBarsPadding()
                        )
//                        EventScreen(
//                            modifier = Modifier
//                                .padding(paddingValues)
//                                .statusBarsPadding()
//                        )
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
