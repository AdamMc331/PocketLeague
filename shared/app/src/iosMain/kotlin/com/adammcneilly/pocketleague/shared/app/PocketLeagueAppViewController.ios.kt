package com.adammcneilly.pocketleague.shared.app

import androidx.compose.ui.window.ComposeUIViewController
import com.adammcneilly.pocketleague.data.event.OctaneGGEventService
import com.adammcneilly.pocketleague.data.event.OfflineFirstEventRepository
import com.adammcneilly.pocketleague.data.event.SQLDelightEventService
import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
import com.adammcneilly.pocketleague.data.match.SQLDelightMatchService

/**
 * Creates a [ComposeUIViewController] which is used on iOS
 * to render composable content. This is just an entry point
 * into our [PocketLeagueApp].
 */
fun pocketLeagueAppViewController() = ComposeUIViewController {
    val database = PocketLeagueDB(DatabaseDriverFactory().createDriver())

    PocketLeagueApp(
        eventRepository = OfflineFirstEventRepository(
            localEventService = SQLDelightEventService(
                database = database,
            ),
            localMatchService = SQLDelightMatchService(
                database = database,
            ),
            remoteEventService = OctaneGGEventService(),
            remoteMatchService = OctaneGGMatchService(),
        ),
    )
}
