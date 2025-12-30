package com.adammcneilly.pocketleague.shared.ui.scaffold.app

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.navigation.AppNavHost
import com.adammcneilly.pocketleague.shared.ui.scaffold.LocalSharedTransitionScope
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme
import org.koin.compose.KoinApplication

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun App(
    modifier: Modifier = Modifier,
) {
    val appState = rememberSaveable(saver = AppState.saver) {
        AppState()
    }

    KoinApplication(
        application = {
            modules()
        },
    ) {
        PocketLeagueTheme {
            Surface {
                SharedTransitionLayout(
                    modifier = modifier
                        .fillMaxSize(),
                ) {
                    CompositionLocalProvider(
                        LocalSharedTransitionScope provides this,
                        LocalAppState provides appState,
                    ) {
                        AppNavHost()
                    }
                }
            }
        }
    }
}
