package com.adammcneilly.pocketleague.android.designsystem.eventstages

import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel

@Composable
fun EventStageListCard(
    displayModels: List<EventStageSummaryDisplayModel>,
    onStageClicked: (String) -> Unit,
) {
    Text(
        text = "Stages",
        style = MaterialTheme.typography.headlineSmall,
    )

    Card {
        with(displayModels) {
            this.forEachIndexed { index, stageSummary ->
                StageSummaryListItem(
                    displayModel = stageSummary,
                    modifier = Modifier
                        .clickable {
                            onStageClicked.invoke(stageSummary.stageId)
                        }
                )

                if (index != this.lastIndex) {
                    Divider()
                }
            }
        }
    }
}
