package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.data.event.LocalEventService
import com.adammcneilly.pocketleague.data.event.SQLDelightEventService
import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.match.impl.LocalMatchService
import com.adammcneilly.pocketleague.data.match.impl.SQLDelightMatchService
import com.adammcneilly.pocketleague.data.player.LocalPlayerService
import com.adammcneilly.pocketleague.data.player.SqlDelightPlayerService
import org.koin.dsl.module

val localModule = module {
    single {
        PocketLeagueDB(get<DatabaseDriverFactory>().createDriver())
    }

    single<LocalMatchService> {
        SQLDelightMatchService(
            database = get(),
        )
    }

    single<LocalEventService> {
        SQLDelightEventService(
            database = get(),
            timeProvider = get(),
        )
    }

    single<LocalPlayerService> {
        SqlDelightPlayerService(
            database = get(),
        )
    }
}
