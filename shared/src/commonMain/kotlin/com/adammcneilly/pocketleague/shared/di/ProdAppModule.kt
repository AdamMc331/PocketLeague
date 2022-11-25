package com.adammcneilly.pocketleague.shared.di

import com.adammcneilly.pocketleague.data.local.DatabaseDriverFactory

/**
 * A concrete implementation of [AppModule] that defines all of the dependencies
 * used in a production scenario.
 */
class ProdAppModule(
    databaseDriverFactory: DatabaseDriverFactory,
) : AppModule {
    override val dataModule: DataModule by lazy {
        ProdDataModule(databaseDriverFactory)
    }
}
