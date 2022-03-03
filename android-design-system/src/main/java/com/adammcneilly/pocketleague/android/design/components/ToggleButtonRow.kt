package com.adammcneilly.pocketleague.android.design.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.design.theme.PocketLeagueTheme

/**
 * Defines any option that can appear inside a [ToggleButtonRow].
 *
 * @param[text] The string that should be displayed to the user inside this button.
 */
data class ToggleButtonOption(
    val text: String,
)

/**
 * A collection of [Color] entities used for each of the buttons inside our [ToggleButtonRow].
 */
data class ToggleButtonRowColors(
    val unselectedBackgroundColor: Color,
    val selectedBackgroundColor: Color,
    val unselectedContentColor: Color,
    val selectedContentColor: Color,
    val borderColor: Color,
)

/**
 * A collection of functions to create default information to be supplied to our [ToggleButtonRow].
 */
object ToggleButtonRowDefaults {

    /**
     * Creates a default implementation of a [ToggleButtonRowColors] entity, while allowing the caller
     * to modify any of these params if necessary.
     */
    @Composable
    fun toggleButtonRowColors(
        unselectedBackgroundColor: Color = MaterialTheme.colorScheme.surface,
        selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
        unselectedContentColor: Color = contentColorFor(unselectedBackgroundColor),
        selectedContentColor: Color = contentColorFor(selectedBackgroundColor),
        borderColor: Color = selectedBackgroundColor,
    ) = ToggleButtonRowColors(
        unselectedBackgroundColor = unselectedBackgroundColor,
        selectedBackgroundColor = selectedBackgroundColor,
        unselectedContentColor = unselectedContentColor,
        selectedContentColor = selectedContentColor,
        borderColor = borderColor,
    )
}

/**
 * Displays a collection of [options] allowing the user to select one of them.
 */
@Composable
fun ToggleButtonRow(
    options: List<ToggleButtonOption>,
    selectedOption: ToggleButtonOption,
    onOptionSelected: (ToggleButtonOption) -> Unit,
    modifier: Modifier = Modifier,
    colors: ToggleButtonRowColors = ToggleButtonRowDefaults.toggleButtonRowColors(),
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = colors.borderColor,
                shape = RoundedCornerShape(50F),
            )
            .clip(RoundedCornerShape(50F))
            .height(52.dp),
    ) {
        options.forEachIndexed { index, option ->
            ToggleButton(
                option = option,
                isSelected = (selectedOption == option),
                colors = colors,
                onClick = {
                    onOptionSelected.invoke(option)
                },
                modifier = Modifier
                    .weight(1F),
            )

            if (index != options.lastIndex) {
                Material3VerticalDivider(
                    color = colors.borderColor,
                )
            }
        }
    }
}

@Composable
private fun ToggleButton(
    option: ToggleButtonOption,
    isSelected: Boolean,
    colors: ToggleButtonRowColors,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (isSelected) {
        colors.selectedBackgroundColor
    } else {
        colors.unselectedBackgroundColor
    }

    val contentColor = if (isSelected) {
        colors.selectedContentColor
    } else {
        colors.unselectedContentColor
    }

    Text(
        text = option.text.toUpperCase(Locale.current),
        modifier = modifier
            .clickable(onClick = onClick)
            .background(
                color = backgroundColor,
            )
            .height(52.dp)
            .padding(12.dp),
        color = contentColor,
        textAlign = TextAlign.Center,
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun ToggleButtonRowPreview() {
    val upcoming = ToggleButtonOption("Upcoming")
    val past = ToggleButtonOption("Past")

    PocketLeagueTheme {
        Surface {
            ToggleButtonRow(
                options = listOf(upcoming, past),
                selectedOption = upcoming,
                onOptionSelected = {},
            )
        }
    }
}
