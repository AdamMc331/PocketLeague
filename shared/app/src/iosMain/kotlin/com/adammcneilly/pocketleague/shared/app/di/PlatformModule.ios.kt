package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    single<DatabaseDriverFactory> {
        DatabaseDriverFactory()
    }
}
