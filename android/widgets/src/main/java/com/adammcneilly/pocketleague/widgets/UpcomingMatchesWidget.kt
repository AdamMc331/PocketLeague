package com.adammcneilly.pocketleague.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.itemsIndexed
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.blueWinner
import com.adammcneilly.pocketleague.core.displaymodels.test.orangeWinner

/**
 * A [GlanceAppWidget] implementation to render upcoming matches in RLCS.
 */
class UpcomingMatchesWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        val demoMatches = listOf(
            MatchDetailDisplayModel.blueWinner,
            MatchDetailDisplayModel.orangeWinner,
            MatchDetailDisplayModel.blueWinner,
        )

        GlanceTheme {
            LazyColumn(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(
                        GlanceTheme.colors.surface,
                    ),
            ) {
                itemsIndexed(demoMatches) { index, match ->
                    Column {
                        UpcomingMatchListItem(
                            displayModel = match,
                        )

                        if (index != demoMatches.lastIndex) {
                            Spacer(
                                modifier = GlanceModifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(GlanceTheme.colors.onSurface),
                            )
                        }
                    }
                }
            }
        }
    }
}
