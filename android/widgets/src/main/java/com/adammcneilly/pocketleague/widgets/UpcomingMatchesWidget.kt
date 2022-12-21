package com.adammcneilly.pocketleague.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
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
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toMatch
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
                    .defaultWeight()
            )

            Image(
                provider = ImageProvider(R.drawable.ic_refresh),
                contentDescription = "Refresh",
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
