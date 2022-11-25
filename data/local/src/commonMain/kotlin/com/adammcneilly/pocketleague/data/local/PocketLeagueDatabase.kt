package com.adammcneilly.pocketleague.data.local

class PocketLeagueDatabase(databaseDriverFactory: DatabaseDriverFactory) {
    val database = PocketLeagueDB(databaseDriverFactory.createDriver())
}
