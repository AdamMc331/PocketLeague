package com.adammcneilly.pocketleague.shared.app

import androidx.compose.ui.window.ComposeUIViewController

/**
 * Creates a [ComposeUIViewController] which is used on iOS
 * to render composable content. This is just an entry point
 * into our [PocketLeagueApp].
 */
fun pocketLeagueAppViewController() = ComposeUIViewController {
    PocketLeagueApp()
}
