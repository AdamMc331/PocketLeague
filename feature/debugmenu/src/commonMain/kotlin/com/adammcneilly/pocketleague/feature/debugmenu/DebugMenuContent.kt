package com.adammcneilly.pocketleague.feature.debugmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme

/**
 * Renders the debug menu.
 */
@Composable
internal fun DebugMenuContent(
    state: DebugMenuScreen.State,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .padding(PocketLeagueTheme.sizes.screenPadding),
    ) {
        timeProviderSection(state)
    }
}

private fun LazyListScope.timeProviderSection(
    state: DebugMenuScreen.State,
) {
    item {
        Text(
            text = "TimeProvider",
            style = MaterialTheme.typography.titleMedium,
        )
    }

    item {
        Row(
            horizontalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.textSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = state.useSystemTimeProvider,
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        val event = DebugMenuScreen.Event.UseSystemTimeProviderChanged(
                            useSystemTimeProvider = true,
                        )
                        state.eventSink.invoke(event)
                    }
                },
            )

            Text(
                text = "Use System Time",
            )
        }
    }

    item {
        Row(
            horizontalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.textSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = !state.useSystemTimeProvider,
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        val event = DebugMenuScreen.Event.UseSystemTimeProviderChanged(
                            useSystemTimeProvider = false,
                        )
                        state.eventSink.invoke(event)
                    }
                },
            )

            Text(
                text = "Use Debug Time",
            )
        }
    }

    item {
        OutlinedTextField(
            value = state.debugTimeProviderDate,
            onValueChange = {
                // Will revisit
            },
            trailingIcon = {
                Icon(
                    Icons.Default.CalendarToday,
                    contentDescription = "Debug Date",
                )
            },
            modifier = Modifier
                .padding(
                    start = (24 + 32).dp,
                ),
            shape = CircleShape,
        )
    }
}
