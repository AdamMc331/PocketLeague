package com.adammcneilly.pocketleague.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

class MyAppWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        GlanceTheme {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(
                        GlanceTheme.colors.surface,
                    ),
            ) {
                Text(
                    text = "Hello, world!",
                    modifier = GlanceModifier
                        .padding(16.dp),
                    style = TextStyle(
                        color = GlanceTheme.colors.onSurface,
                    )
                )
            }
        }
    }
}
