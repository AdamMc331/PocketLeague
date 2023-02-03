package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.records.RecordsViewState

/**
 * The composable to render the [viewState] of the records screen.
 */
@Composable
fun RecordsContent(
    viewState: RecordsViewState,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "Records screen: $viewState")
    }
}
