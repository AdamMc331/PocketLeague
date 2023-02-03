package com.adammcneilly.pocketleague.shared.di

/**
 * A concrete implementation of [AppModule] that defines all of the dependencies
 * used in a production scenario.
 */
class ProdAppModule(
    override val platformModule: PlatformModule
) : AppModule {

    override val dataModule: DataModule by lazy {
        ProdDataModule(platformModule.databaseDriverFactory)
    }
}
