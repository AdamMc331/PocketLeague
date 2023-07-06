package com.adammcneilly.pocketleague.data.local.sqldelight

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

/**
 * An implementation of [DatabaseDriverFactory] for the iOS platform.
 */
actual class DatabaseDriverFactory {

    /**
     * Create the [SqlDriver] using [NativeSqliteDriver].
     */
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = PocketLeagueDB.Schema,
            name = "pocketleague.db",
        )
    }
}
