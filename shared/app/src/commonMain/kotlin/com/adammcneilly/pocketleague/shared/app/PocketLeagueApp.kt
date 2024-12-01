package com.adammcneilly.pocketleague.shared.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adammcneilly.pocketleague.shared.app.di.allModules
import com.adammcneilly.pocketleague.shared.app.feature.feed.FeedScreen
import com.adammcneilly.pocketleague.shared.app.ui.theme.PocketLeagueTheme
import org.koin.compose.KoinApplication

/**
 * Main composable entrypoint to the shared multiplatform version of
 * the pocket league app.
 */
@Composable
fun PocketLeagueApp(
    modifier: Modifier = Modifier,
) {
    KoinApplication(
        application = {
            modules(allModules)
        },
    ) {
        PocketLeagueTheme {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "feed",
                modifier = modifier,
            ) {
                composable("feed") {
                    FeedScreen(
                        modifier = Modifier
                            .fillMaxSize(),
                    )
                }
            }
        }
    }
}
