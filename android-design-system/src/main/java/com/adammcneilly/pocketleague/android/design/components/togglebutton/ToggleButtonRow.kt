package com.adammcneilly.pocketleague.android.design.components.togglebutton

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.adammcneilly.pocketleague.android.design.adaptiveWidth
import com.adammcneilly.pocketleague.android.design.getValue
import com.adammcneilly.pocketleague.android.design.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.core.ui.UIText

/**
 * A row of [ToggleButton] entities that allows the user to select one of the supplied [options].
 *
 * @param[options] All possible selections the user can make in this button row.
 * @param[onOptionSelected] A callback triggered whenever we want to change the [selectedOption].
 * @param[modifier] An optional [Modifier] to configure this button row.
 * @param[selectedOption] The currently selected choice by the user. Defaults to the first item in
 * the [options] list.
 * @param[cornerRadiusPercent] The percentage of our height to use as the radius for the corners. Defaults
 * to 50% to give a rounded pill shape.
 */
@Composable
fun ToggleButtonRow(
    options: List<ToggleButtonOption>,
    onOptionSelected: (ToggleButtonOption) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: ToggleButtonOption = options.first(),
    cornerRadiusPercent: Int = 50,
) {
    val startShape = RoundedCornerShape(
        topStartPercent = cornerRadiusPercent,
        topEndPercent = 0,
        bottomStartPercent = cornerRadiusPercent,
        bottomEndPercent = 0,
    )

    val middleShape = RoundedCornerShape(0.dp)

    val endShape = RoundedCornerShape(
        topStartPercent = 0,
        topEndPercent = cornerRadiusPercent,
        bottomStartPercent = 0,
        bottomEndPercent = cornerRadiusPercent,
    )

    Row(
        modifier = modifier
            .adaptiveWidth(
                mediumWidthRatio = 0.5F,
            ),
    ) {
        options.forEachIndexed { index, option ->
            val shapeToUse = when (index) {
                0 -> startShape
                options.lastIndex -> endShape
                else -> middleShape
            }

            val isOptionSelected = (option == selectedOption)

            ToggleButton(
                shape = shapeToUse,
                onClick = {
                    onOptionSelected.invoke(option)
                },
                text = option.text.getValue(),
                selected = isOptionSelected,
                modifier = Modifier
                    .adjustOffsetZIndex(
                        indexInRow = index,
                        isIndexSelected = isOptionSelected,
                    )
                    .weight(1F),
            )
        }
    }
}

/**
 * A custom [Modifier] that will adjust the offset of the current item (if necessary), as well as
 * it's Z index. This prevents seeing a double width divider on two buttons next to each other.
 *
 * Inspiration: https://stackoverflow.com/a/67036710/3131147
 */
private fun Modifier.adjustOffsetZIndex(
    indexInRow: Int,
    isIndexSelected: Boolean,
): Modifier {
    val zIndex = if (isIndexSelected) {
        1F
    } else {
        0F
    }

    return when (indexInRow) {
        0 -> {
            this
                .offset(0.dp, 0.dp)
                .zIndex(zIndex)
        }
        else -> {
            val offset = -1 * indexInRow

            this
                .offset(offset.dp, 0.dp)
                .zIndex(zIndex)
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Medium",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 800,
)
@Preview(
    name = "Expanded",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 900,
)
@Composable
private fun ToggleButtonRowPreview() {
    val upcoming = ToggleButtonOption(UIText.StringText("Upcoming"))
    val past = ToggleButtonOption(UIText.StringText("Past"))

    PocketLeagueTheme {
        Surface {
            ToggleButtonRow(
                options = listOf(upcoming, past),
                selectedOption = upcoming,
                modifier = Modifier
                    .padding(16.dp),
                onOptionSelected = {},
            )
        }
    }
}
