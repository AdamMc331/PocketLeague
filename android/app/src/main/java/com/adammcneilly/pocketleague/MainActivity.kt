package com.adammcneilly.pocketleague

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
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
import com.adammcneilly.pocketleague.android.designsystem.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.notifications.PocketLeagueNotificationManager
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

            setupNotificationChannels()
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

    /**
     * For Android 8.0 and up, we need to support channels for notifications giving users
     * more granular control over what they receive.
     *
     * https://developer.android.com/develop/ui/views/notifications/build-notification#Priority
     */
    private fun setupNotificationChannels() {
        // For now we just have one channel, but if we had others, we can group them by (id, name, description)
        // and run a for loop.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Match started"
            val descriptionText = "Be notified when a match is starting"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(
                PocketLeagueNotificationManager.MATCH_STARTED_CHANNEL_ID,
                name,
                importance,
            ).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }
}
