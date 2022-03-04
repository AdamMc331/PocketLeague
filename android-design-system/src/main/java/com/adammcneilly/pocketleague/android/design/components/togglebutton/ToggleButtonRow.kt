package com.adammcneilly.pocketleague.android.design.components.togglebutton

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.adammcneilly.pocketleague.android.design.theme.PocketLeagueTheme

/**
 * TBD
 */
@Composable
fun ToggleButtonRow(
    options: List<ToggleButtonOption>,
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
            .fillMaxWidth(),
    ) {
        options.forEachIndexed { index, option ->
            val shapeToUse = when (index) {
                0 -> startShape
                options.lastIndex -> endShape
                else -> middleShape
            }

            ToggleButton(
                shape = shapeToUse,
                onClick = {},
                text = option.text,
                selected = (option == selectedOption),
                modifier = when (index) {
                    0 -> {
                        if (selectedOption == option) {
                            Modifier
                                .offset(0.dp, 0.dp)
                                .zIndex(1f)
                        } else {
                            Modifier
                                .offset(0.dp, 0.dp)
                                .zIndex(0f)
                        }
                    }
                    else -> {
                        val offset = -1 * index
                        if (selectedOption == option) {
                            Modifier
                                .offset(offset.dp, 0.dp)
                                .zIndex(1f)
                        } else {
                            Modifier
                                .offset(offset.dp, 0.dp)
                                .zIndex(0f)
                        }
                    }
                }
                    .weight(1F),
            )
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
@Composable
private fun ToggleButtonRowPreview() {
    val upcoming = ToggleButtonOption("Upcoming")
    val past = ToggleButtonOption("Past")

    PocketLeagueTheme {
        Surface {
            ToggleButtonRow(
                options = listOf(upcoming, past),
                selectedOption = upcoming,
                modifier = Modifier
                    .padding(16.dp),
            )
        }
    }
}
