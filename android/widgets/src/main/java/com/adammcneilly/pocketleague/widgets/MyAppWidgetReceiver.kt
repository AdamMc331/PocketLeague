package com.adammcneilly.pocketleague.widgets

import androidx.glance.appwidget.GlanceAppWidgetReceiver

class MyAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget = MyAppWidget()
}
