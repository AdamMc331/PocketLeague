package com.adammcneilly.pocketleague.shared.core.ui

/**
 * A collection of possible representations of text in our UI layer.
 */
sealed class UIText {
    /**
     * Text that should be fetched from resources using the supplied [stringRes].
     */
    data class AndroidResourceText(val stringRes: Int) : UIText()

    /**
     * Text that is presented as a [string].
     */
    data class StringText(val string: String) : UIText()
}
