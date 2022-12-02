package com.adammcneilly.pocketleague.widgets

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.adammcneilly.pocketleague.data.match.MatchService
import kotlinx.coroutines.flow.firstOrNull

class UpcomingMatchesWidgetWorker(
    private val service: MatchService,
    private val appContext: Context,
    private val workerParameters: WorkerParameters,
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        val glanceId = GlanceAppWidgetManager(appContext)
            .getGlanceIds(UpcomingMatchesWidget::class.java)
            .first()

        val upcomingMatches = service
            .getUpcomingMatches()
            .firstOrNull()

        // Need to save upcoming matches state

        return Result.success()
    }
}
