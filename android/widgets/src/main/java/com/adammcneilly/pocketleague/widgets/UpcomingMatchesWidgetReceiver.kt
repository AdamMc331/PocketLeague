package com.adammcneilly.pocketleague.widgets

import androidx.glance.appwidget.GlanceAppWidgetReceiver

/**
 * The necessary [GlanceAppWidgetReceiver] for providing a [glanceAppWidget].
 */
class UpcomingMatchesWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget = UpcomingMatchesWidget()
}
