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
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel

/**
 * A [GlanceAppWidget] implementation to render upcoming matches in RLCS.
 */
class UpcomingMatchesWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        val demoMatches = listOf(
            TestDisplayModel.matchDetailBlueWinner,
            TestDisplayModel.matchDetailOrangeWinner,
            TestDisplayModel.matchDetailBlueWinner,
        )

        GlanceTheme {
            LazyColumn(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(
                        GlanceTheme.colors.surfaceVariant,
                    ),
            ) {
                itemsIndexed(demoMatches) { index, match ->
                    Column {
                        UpcomingMatchListItem(
                            displayModel = match,
                            contentColor = GlanceTheme.colors.onSurfaceVariant,
                        )

                        if (index != demoMatches.lastIndex) {
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
