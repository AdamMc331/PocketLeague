package com.adammcneilly.pocketleague.shared.ui.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel

/**
 * A [Dialog] component to render detailed information about a given [game].
 */
@Composable
fun GameDetailDialog(
    game: GameDetailDisplayModel,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        GameDetailContent(game)
    }
}
