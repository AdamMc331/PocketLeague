package com.adammcneilly.pocketleague.data.local

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.requery.android.database.sqlite.RequerySQLiteOpenHelperFactory

/**
 * An implementation of [DatabaseDriverFactory] for the Android platform.
 */
actual class DatabaseDriverFactory(private val context: Context) {

    /**
     * Create the [SqlDriver] using [AndroidSqliteDriver].
     */
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = PocketLeagueDB.Schema,
            context = context,
            name = "pocketleague.db",
            factory = RequerySQLiteOpenHelperFactory(),
        )
    }
}
