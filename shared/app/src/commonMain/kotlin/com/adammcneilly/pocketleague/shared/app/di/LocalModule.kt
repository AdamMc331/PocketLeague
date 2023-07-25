package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.data.event.LocalEventService
import com.adammcneilly.pocketleague.data.event.SQLDelightEventService
import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.match.LocalMatchService
import com.adammcneilly.pocketleague.data.match.SQLDelightMatchService
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
            clock = get(),
        )
    }
}
