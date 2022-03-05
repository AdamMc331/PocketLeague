package com.adammcneilly.pocketleague.android.design.components.togglebutton

import com.adammcneilly.pocketleague.core.ui.UIText

/**
 * Defines any option that can appear inside a [ToggleButtonRow].
 *
 * @property[text] The string that should be displayed to the user inside this button.
 */
data class ToggleButtonOption(
    val text: UIText,
)
