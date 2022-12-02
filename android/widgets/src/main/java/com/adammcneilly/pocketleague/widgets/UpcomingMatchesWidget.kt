package com.adammcneilly.pocketleague.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.itemsIndexed
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.local.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.mappers.toMatch
import com.adammcneilly.pocketleague.sqldelight.MatchWithEventAndTeams

/**
 * A [GlanceAppWidget] implementation to render upcoming matches in RLCS.
 */
class UpcomingMatchesWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        val context = LocalContext.current

        val database = PocketLeagueDB(DatabaseDriverFactory(context).createDriver())

        // This works to render from DB.
        // Downfall is that query DB on every composition (though I don't think this triggers often?).
        // We also lose the sync with network data (this only works because we did a sync once before).
        // Maybe pull from DB when rendered, supply a refresh button to trigger a workmanager flow.
        val matchesToShow = database
            .localMatchQueries
            .selectUpcoming()
            .executeAsList()
            .map(MatchWithEventAndTeams::toMatch)
            .map(Match::toDetailDisplayModel)

        println("ARM - Composition triggered: ${matchesToShow.size}")

        GlanceTheme {
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
}
