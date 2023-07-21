package com.adammcneilly.pocketleague.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.adammcneilly.pocketleague.data.event.OctaneGGEventService
import com.adammcneilly.pocketleague.data.event.OfflineFirstEventRepository
import com.adammcneilly.pocketleague.data.event.SQLDelightEventService
import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
import com.adammcneilly.pocketleague.data.match.SQLDelightMatchService
import com.adammcneilly.pocketleague.shared.app.PocketLeagueApp
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * The main entry point into the application from the Android side.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val database = PocketLeagueDB(DatabaseDriverFactory(this).createDriver())

        val eventRepository = OfflineFirstEventRepository(
            localEventService = SQLDelightEventService(
                database = database,
            ),
            localMatchService = SQLDelightMatchService(
                database = database,
            ),
            remoteEventService = OctaneGGEventService(),
            remoteMatchService = OctaneGGMatchService(),
        )

        setContent {
            PocketLeagueTheme {
                SetSystemBarsTransparent()

                PocketLeagueApp(
                    eventRepository = eventRepository,
                )
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
                darkIcons = useDarkIcons,
            )
        }
    }
}
