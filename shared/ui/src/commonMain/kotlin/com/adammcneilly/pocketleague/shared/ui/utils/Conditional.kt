package com.adammcneilly.pocketleague.shared.ui.utils

import androidx.compose.ui.Modifier

/**
 * Conditionally applies the supplied [modifier] only if the [condition] is true.
 */
fun Modifier.conditional(
    condition: Boolean,
    modifier: Modifier.() -> Modifier,
): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}
