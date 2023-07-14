package com.adammcneilly.pocketleague.data.local.sqldelight

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

/**
* An implementation of [DatabaseDriverFactory] for the JVM platform.
*/
actual class DatabaseDriverFactory {

    /**
     * Create the [SqlDriver] using [JdbcSqliteDriver].
     */
    actual fun createDriver(): SqlDriver {
        return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    }
}
