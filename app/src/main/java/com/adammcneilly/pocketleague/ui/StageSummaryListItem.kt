package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme

@Composable
fun StageSummaryListItem(
    displayModel: EventStageSummaryDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = displayModel.name,
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = displayModel.dateString,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun StageSummaryListItemPreview() {
    val displayModel = EventStageSummaryDisplayModel(
        stageId = "123",
        name = "Closed Qualifier",
        startDate = "May 22, 2022",
        endDate = "May 23, 2022",
    )

    PocketLeagueTheme {
        Surface {
            StageSummaryListItem(
                displayModel = displayModel,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
