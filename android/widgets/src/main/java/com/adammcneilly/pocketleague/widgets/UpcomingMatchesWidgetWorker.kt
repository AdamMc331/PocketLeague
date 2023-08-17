package com.adammcneilly.pocketleague.widgets

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
import com.adammcneilly.pocketleague.data.match.OfflineFirstMatchRepository
import com.adammcneilly.pocketleague.data.match.SQLDelightMatchService
import com.adammcneilly.pocketleague.data.octanegg.OctaneGGAPIClient
import kotlinx.datetime.Clock

/**
 * An implementation of [CoroutineWorker] to request and persist upcoming matches.
 * It will trigger a widget update upon success.
 */
class UpcomingMatchesWidgetWorker(
    private val appContext: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(appContext, workerParameters) {

    /**
     * In a perfect world we may not want to create this dependency
     * directly so I could consider writing some tests
     * for our widget. However, because this module doesn't have any DI
     * frameworks we'll just make it work.
     */
    private val repository: MatchRepository by lazy {
        OfflineFirstMatchRepository(
            localDataSource = SQLDelightMatchService(PocketLeagueDB(DatabaseDriverFactory(appContext).createDriver())),
            remoteDataSource = OctaneGGMatchService(
                apiClient = OctaneGGAPIClient,
                clock = Clock.System,
            ),
        )
    }

    @Suppress("ReturnCount")
    override suspend fun doWork(): Result {
        val glanceId = GlanceAppWidgetManager(appContext)
            .getGlanceIds(UpcomingMatchesWidget::class.java)
            .firstOrNull()

        println("ARM - glanceId: $glanceId")

        if (glanceId == null) {
            return Result.failure(
                Data
                    .Builder()
                    .putString("FAILURE_REASON", "NULL_GLANCE_ID")
                    .build(),
            )
        }

        val result = repository.fetchAndPersistUpcomingMatches()

        result.fold(
            onSuccess = {
                println("ARM - Success fetching upcoming matches.")
                UpcomingMatchesWidget().apply {
                    update(appContext, glanceId)
                }

                return Result.success(
                    Data
                        .Builder()
                        .putString("FAILURE_REASON", "NONE")
                        .build(),
                )
            },
            onFailure = {
                println("ARM - Error fetching & persisting upcoming matches.")
                // Should we surface an error?
                return Result.failure(
                    Data
                        .Builder()
                        .putString("FAILURE_REASON", "FAILED_REQUEST")
                        .build(),
                )
            },
        )
    }
}
