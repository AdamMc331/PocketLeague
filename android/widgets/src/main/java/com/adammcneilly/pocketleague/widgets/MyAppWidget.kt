package com.adammcneilly.pocketleague.widgets

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.text.Text

class MyAppWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        Text(
            text = "Hello, world!",
        )
    }
}
