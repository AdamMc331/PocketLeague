package com.adammcneilly.pocketleague.widgets

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetReceiver

/**
 * The necessary [GlanceAppWidgetReceiver] for providing a [glanceAppWidget].
 */
class UpcomingMatchesWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget = UpcomingMatchesWidget()

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)

        context?.startUpcomingMatchesWorker()
    }
}
