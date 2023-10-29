package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

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
 * @param[textColor] If supplied, use this color for our text component.
 * @param[iconTint] If supplied, we provide a tint to our [icon] using this color.
 * @param[textAlign] @see [Text]
 * @param[style] @see [Text]
 * @param[fontWeight] @see [Text]
 */
@Composable
fun InlineIconText(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    leadingIcon: Boolean = false,
    showIcon: Boolean = true,
    textColor: Color = LocalContentColor.current,
    iconTint: Color = LocalContentColor.current,
    textAlign: TextAlign? = null,
    style: TextStyle = LocalTextStyle.current,
    fontWeight: FontWeight? = null,
) {
    Text(
        text =
            text.getAnnotatedString(
                leadingIcon = leadingIcon,
                showIcon = showIcon,
            ),
        inlineContent =
            getInlineContent(
                icon = icon,
                showIcon = showIcon,
                iconTint = iconTint,
            ),
        modifier = modifier,
        textAlign = textAlign,
        style = style,
        color = textColor,
        fontWeight = fontWeight,
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
    iconTint: Color,
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
                    ),
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                    )
                },
            ),
        )
    }
}
