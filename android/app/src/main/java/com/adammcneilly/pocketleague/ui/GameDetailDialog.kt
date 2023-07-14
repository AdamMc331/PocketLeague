package com.adammcneilly.pocketleague.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.shared.ui.game.GameDetailContent

/**
 * Renders a [GameDetailContent] composable inside a dialog.
 */
@Composable
fun GameDetailDialog(
    displayModel: GameDetailDisplayModel,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        GameDetailContent(displayModel = displayModel)
    }
}
