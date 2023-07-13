package com.adammcneilly.pocketleague.shared.ui.game

import androidx.compose.runtime.Composable
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.shared.ui.components.Dialog
import com.adammcneilly.pocketleague.shared.ui.components.DialogProperties

/**
 * A [Dialog] component to render detailed information about a given [game].
 */
@Composable
fun GameDetailDialog(
    game: GameDetailDisplayModel,
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(),
    ) {
        GameDetailContent(game)
    }
}
