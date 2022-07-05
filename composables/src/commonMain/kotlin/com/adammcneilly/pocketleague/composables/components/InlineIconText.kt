package com.adammcneilly.pocketleague.composables.components

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString

private const val INLINE_CONTENT_ID = "inlineContent"

/**
 * Creates a text composable to show an inline icon. This is usually used to show a trophy
 * inline with a winning team's name.
 *
 * @param[text] The string to render inside the text composable.
 * @param[icon] The icon that will be added to this text.
 * @param[modifier] An optional [Modifier] to customize this text.
 * @param[leadingIcon] If true, the icon will show before the text. If false, the icon
 * appears after.
 * @param[showIcon] If true, we'll actually show the icon. If false, we'll ignore it.
 */
@Composable
fun InlineIconText(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    leadingIcon: Boolean = false,
    showIcon: Boolean = true,
) {
    Text(
        text = text.getAnnotatedString(
            leadingIcon = leadingIcon,
            showIcon = showIcon,
        ),
        inlineContent = getInlineContent(
            icon = icon,
            showIcon = showIcon,
        ),
        modifier = modifier,
    )
}

/**
 * Builds the [AnnotatedString] that has the inline content for our text.
 */
private fun String.getAnnotatedString(
    leadingIcon: Boolean,
    showIcon: Boolean,
): AnnotatedString {
    return buildAnnotatedString {
        if (leadingIcon && showIcon) {
            appendInlineContent(INLINE_CONTENT_ID, "[icon]")
            append(" ")
        }

        append(this@getAnnotatedString)

        if (!leadingIcon && showIcon) {
            append(" ")
            appendInlineContent(INLINE_CONTENT_ID, "[icon]")
        }
    }
}

/**
 * Builds the inline text content for an [InlineIconText].
 */
@Composable
private fun getInlineContent(
    icon: ImageVector,
    showIcon: Boolean,
): Map<String, InlineTextContent> {
    return if (!showIcon) {
        emptyMap()
    } else {
        mapOf(
            Pair(
                INLINE_CONTENT_ID,
                InlineTextContent(
                    Placeholder(
                        width = LocalTextStyle.current.fontSize,
                        height = LocalTextStyle.current.fontSize,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
                    )
                ) {
                    Icon(
                        icon,
                        contentDescription = null,
                    )
                }
            )
        )
    }
}
