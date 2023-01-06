package com.adammcneilly.pocketleague.widgets

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
import com.adammcneilly.pocketleague.data.match.OfflineFirstMatchRepository
import com.adammcneilly.pocketleague.data.match.SQLDelightMatchService

class UpcomingMatchesWidgetWorker(
    private val appContext: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(appContext, workerParameters) {

    private val repository: MatchRepository by lazy {
        OfflineFirstMatchRepository(
            localDataSource = SQLDelightMatchService(PocketLeagueDB(DatabaseDriverFactory(appContext).createDriver())),
            remoteDataSource = OctaneGGMatchService(),
        )
    }

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

        return when (result) {
            is DataState.Error -> {
                println("ARM - Error fetching & persisting upcoming matches.")
                // Should we surface an error?
                Result.failure(
                    Data
                        .Builder()
                        .putString("FAILURE_REASON", "FAILED_REQUEST")
                        .build(),
                )
            }

            is DataState.Success -> {
                println("ARM - Success fetching upcoming matches.")
                UpcomingMatchesWidget().apply {
                    update(appContext, glanceId)
                }

                Result.success(
                    Data
                        .Builder()
                        .putString("FAILURE_REASON", "NONE")
                        .build(),
                )
            }
        }
    }
}
