package com.adammcneilly.pocketleague.core.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

sealed class UIText {
    data class ResourceText(@StringRes val stringRes: Int) : UIText()

    data class StringText(val string: String) : UIText()
}

fun UIText.getValue(context: Context): String {
    return when (this) {
        is UIText.ResourceText -> {
            context.getString(this.stringRes)
        }
        is UIText.StringText -> {
            this.string
        }
    }
}

@Composable
fun UIText.getValue(): String {
    return this.getValue(LocalContext.current)
}
