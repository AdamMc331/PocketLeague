package com.adammcneilly.pocketleague.shared.ui.brackets.swiss

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.SwissStageTeamResultDisplayModel

/**
 * Given a list of [teamResults] render those in order.
 */
@Composable
fun SwissStageTeamResultList(
    teamResults: List<SwissStageTeamResultDisplayModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        itemsIndexed(teamResults) { index, teamResult ->
            SwissStageTeamResultListItem(teamResult)

            if (index != teamResults.lastIndex) {
                Divider()
            }
        }
    }
}
