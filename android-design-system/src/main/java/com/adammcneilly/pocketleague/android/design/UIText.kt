package com.adammcneilly.pocketleague.android.design

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.adammcneilly.pocketleague.shared.core.ui.UIText

/**
 * Given a [context], look up the String res if necessary, or resolve it right from the
 * [UIText.StringText] class.
 */
fun UIText.getValue(context: Context): String {
    return when (this) {
        is UIText.AndroidResourceText -> {
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
