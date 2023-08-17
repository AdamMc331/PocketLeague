package com.adammcneilly.pocketleague.widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.itemsIndexed
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toMatch
import com.adammcneilly.pocketleague.sqldelight.MatchWithEventAndTeams
import kotlinx.datetime.Clock
import java.util.concurrent.TimeUnit

/**
 * A [GlanceAppWidget] implementation to render upcoming matches in RLCS.
 */
class UpcomingMatchesWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        val context = LocalContext.current

        val database = PocketLeagueDB(DatabaseDriverFactory(context).createDriver())

        // Fetch latest match information from the database.
        val matchesToShow = database
            .localMatchQueries
            .selectUpcoming()
            .executeAsList()
            .map(MatchWithEventAndTeams::toMatch)
            .map { match ->
                match.toDetailDisplayModel(Clock.System)
            }

        println("ARM - Rendering matches: ${matchesToShow.size}")

        GlanceTheme {
            Column(
                modifier = GlanceModifier
                    .fillMaxWidth(),
            ) {
                WidgetToolbar()

                UpcomingMatchesLazyColumn(matchesToShow)
            }
        }
    }

    @Composable
    private fun WidgetToolbar() {
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(GlanceTheme.colors.primaryContainer),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Upcoming Matches",
                style = TextStyle(
                    color = GlanceTheme.colors.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = GlanceModifier
                    .defaultWeight(),
            )

            Image(
                provider = ImageProvider(R.drawable.ic_refresh),
                contentDescription = "Refresh",
                modifier = GlanceModifier
                    .clickable(onClick = actionRunCallback<WidgetRefreshAction>()),
            )
        }
    }

    @Composable
    private fun UpcomingMatchesLazyColumn(matchesToShow: List<MatchDetailDisplayModel>) {
        LazyColumn(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(
                    GlanceTheme.colors.surfaceVariant,
                ),
        ) {
            itemsIndexed(matchesToShow) { index, match ->
                Column {
                    UpcomingMatchListItem(
                        displayModel = match,
                        contentColor = GlanceTheme.colors.onSurfaceVariant,
                    )

                    if (index != matchesToShow.lastIndex) {
                        Spacer(
                            modifier = GlanceModifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(GlanceTheme.colors.onSurfaceVariant),
                        )
                    }
                }
            }
        }
    }
}

/**
 * This is an implementation of [ActionCallback] which refreshes our widget
 * after making a request to trigger our [UpcomingMatchesWidgetWorker].
 */
class WidgetRefreshAction : ActionCallback {
    override suspend fun onAction(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        // Trigger a one time work request to fetch and persist
        // upcoming matches.
        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<UpcomingMatchesWidgetWorker>()
            .build()

        WorkManager.getInstance(context)
            .enqueue(oneTimeWorkRequest)
    }
}

/**
 * Start a process to continually update the upcoming matches widget at
 * 15 minute intervals.
 */
@Suppress("MagicNumber")
fun Context.startUpcomingMatchesWorker() {
    val networkConstraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

    val request = PeriodicWorkRequest
        .Builder(UpcomingMatchesWidgetWorker::class.java, 15, TimeUnit.MINUTES)
        .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 5000L, TimeUnit.MILLISECONDS)
        .setConstraints(networkConstraint)
        .build()

    val uniqueTag = "UPCOMING_MATCHES_WIDGET"

    WorkManager.getInstance(this)
        .enqueueUniquePeriodicWork(
            uniqueTag,
            ExistingPeriodicWorkPolicy.REPLACE,
            request,
        )
}
