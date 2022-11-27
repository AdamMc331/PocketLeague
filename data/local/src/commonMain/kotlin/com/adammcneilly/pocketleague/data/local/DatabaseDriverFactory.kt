package com.adammcneilly.pocketleague.data.local

import com.squareup.sqldelight.db.SqlDriver

/**
 * An expected class for defining a [SqlDriver] depending on the platform we're using.
 */
expect class DatabaseDriverFactory {

    /**
     * Creates the [SqlDriver] instance.
     */
    fun createDriver(): SqlDriver
}
