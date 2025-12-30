package com.adammcneilly.pocketleague.shared.feature.eventlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.displaymodels.EventDisplayModel
import com.adammcneilly.pocketleague.shared.models.EventType
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueColors
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme

@Composable
fun EventListCard(
    event: EventDisplayModel,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.background,
        border = BorderStroke(
            width = 1.dp,
            color = borderColor(event),
        ),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Text(
                text = event.name,
            )
        }
    }
}

@Composable
private fun borderColor(
    event: EventDisplayModel,
): Color {
    return when {
        event.isLive -> {
            PocketLeagueTheme.colors.live
        }
        event.type == EventType.MAJOR -> {
            PocketLeagueTheme.colors.major
        }
        event.type == EventType.WORLDS -> {
            PocketLeagueTheme.colors.worlds
        }
        else -> {
            PocketLeagueTheme.colors.upcoming
        }
    }
}
