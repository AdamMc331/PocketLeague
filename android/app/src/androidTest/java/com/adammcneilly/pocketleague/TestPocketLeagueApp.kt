package com.adammcneilly.pocketleague

import com.adammcneilly.pocketleague.shared.di.DataModule
import com.adammcneilly.pocketleague.shared.test.TestDataModule

class TestPocketLeagueApp : PocketLeagueApp() {

    override val dataModule: DataModule = TestDataModule()
}
