package com.adammcneilly.pocketleague.android.designsystem.modifiers

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import com.google.accompanist.placeholder.material.placeholder

/**
 * A wrapper around an accompanist [placeholder] that provides some defaults for the
 * Pocket League Android app.
 */
fun Modifier.pocketLeaguePlaceholder(
    visible: Boolean,
) = this.placeholder(
    visible = visible,
    shape = CircleShape,
)
