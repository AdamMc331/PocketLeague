package com.adammcneilly.pocketleague

import com.adammcneilly.pocketleague.shared.di.AppModule
import com.adammcneilly.pocketleague.shared.di.DataModule
import com.adammcneilly.pocketleague.shared.test.TestDataModule

class TestPocketLeagueApp : PocketLeagueApp() {

    override val appModule: AppModule = object : AppModule {
        override val dataModule: DataModule = TestDataModule()
    }
}
