package com.adammcneilly.pocketleague.shared.ui.swiss

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.SwissTeamResultDisplayModel
import com.adammcneilly.pocketleague.shared.ui.components.ListItemDividerCard

@Composable
fun SwissTeamResultListCard(
    teamResults: List<SwissTeamResultDisplayModel>,
    modifier: Modifier = Modifier,
) {
    ListItemDividerCard(
        items = teamResults,
        modifier = modifier,
    ) { teamResult ->
        SwissTeamResultListItem(
            displayModel = teamResult,
            modifier = Modifier
                .padding(8.dp),
        )
    }
}
