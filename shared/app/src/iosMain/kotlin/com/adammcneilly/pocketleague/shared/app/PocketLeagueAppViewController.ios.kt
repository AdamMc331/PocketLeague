package com.adammcneilly.pocketleague.shared.app

import androidx.compose.ui.window.ComposeUIViewController
import com.adammcneilly.pocketleague.data.event.OctaneGGEventService
import com.adammcneilly.pocketleague.data.event.OfflineFirstEventRepository
import com.adammcneilly.pocketleague.data.event.SQLDelightEventService
import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB

/**
 * Creates a [ComposeUIViewController] which is used on iOS
 * to render composable content. This is just an entry point
 * into our [PocketLeagueApp].
 */
fun pocketLeagueAppViewController() = ComposeUIViewController {
    PocketLeagueApp(
        eventRepository = OfflineFirstEventRepository(
            localEventService = SQLDelightEventService(
                database = PocketLeagueDB(DatabaseDriverFactory().createDriver()),
            ),
            remoteEventService = OctaneGGEventService(),
        ),
    )
}
