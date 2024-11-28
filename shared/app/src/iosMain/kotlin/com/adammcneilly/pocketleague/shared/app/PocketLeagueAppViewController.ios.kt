package com.adammcneilly.pocketleague.shared.app

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

/**
 * Creates a [ComposeUIViewController] which is used on iOS
 * to render composable content. This is just an entry point
 * into our [PocketLeagueApp].
 */
fun pocketLeagueAppViewController(): UIViewController {
    return ComposeUIViewController {
        PocketLeagueApp()
    }
}
