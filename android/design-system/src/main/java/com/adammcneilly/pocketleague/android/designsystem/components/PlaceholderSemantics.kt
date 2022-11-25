package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val PlaceholderSemanticsKey = SemanticsPropertyKey<Boolean>("Placeholder")
var SemanticsPropertyReceiver.isPlaceholder by PlaceholderSemanticsKey
