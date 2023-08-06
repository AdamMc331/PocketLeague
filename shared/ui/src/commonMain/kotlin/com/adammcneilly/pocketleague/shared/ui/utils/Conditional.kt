package com.adammcneilly.pocketleague.shared.ui.utils

import androidx.compose.ui.Modifier

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
