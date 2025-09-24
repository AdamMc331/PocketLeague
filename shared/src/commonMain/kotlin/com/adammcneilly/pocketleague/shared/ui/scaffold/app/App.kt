package com.adammcneilly.pocketleague.shared.ui.scaffold.app

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.ui.scaffold.LocalSharedTransitionScope
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme
import org.koin.compose.KoinApplication

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun App(
    appState: AppState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
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
                        content()
                    }
                }
            }
        }
    }
}
