package com.adammcneilly.pocketleague.core.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * A collection of possible representations of text in our UI layer.
 */
sealed class UIText {
    /**
     * Text that should be fetched from resources using the supplied [stringRes].
     */
    data class ResourceText(@StringRes val stringRes: Int) : UIText()

    /**
     * Text that is presented as a [string].
     */
    data class StringText(val string: String) : UIText()
}

/**
 * Given a [context], look up the String res if necessary, or resolve it right from the
 * [UIText.StringText] class.
 */
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

/**
 * In a [Composable] context, provide a helper function to get the value which just proxies to
 * the other [getValue] method.
 */
@Composable
fun UIText.getValue(): String {
    return this.getValue(LocalContext.current)
}
