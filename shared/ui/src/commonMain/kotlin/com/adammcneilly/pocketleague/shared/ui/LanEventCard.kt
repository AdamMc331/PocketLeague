package com.adammcneilly.pocketleague.shared.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.design.system.theme.md_theme_dark_onSurface
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsBlue
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsOrange
import com.adammcneilly.pocketleague.shared.ui.utils.VerticalSpacer

@Composable
fun LanEventCard(
    modifier: Modifier = Modifier,
) {
    val rlcsGradientBrush = Brush.horizontalGradient(
        colors = listOf(rlcsBlue, rlcsOrange),
    )

    CompositionLocalProvider(
        LocalContentColor provides md_theme_dark_onSurface,
    ) {
        Box(
            modifier = modifier
                .background(
                    brush = rlcsGradientBrush,
                    shape = MaterialTheme.shapes.medium,
                )
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                Text(
                    text = "RLCS 2022-23 World Championship",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                VerticalSpacer(16.dp)

                Text(
                    text = "August 3 - 13, 2023",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                Text(
                    text = "PSD Bank Dome – Düsseldorf, Germany",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        }
    }
}
