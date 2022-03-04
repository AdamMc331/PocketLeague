package com.adammcneilly.pocketleague.android.design.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.intl.Locale
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

@Composable
fun ToggleButton(
    shape: Shape,
    onClick: () -> Unit,
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    val colorsToUse = if (selected) {
        ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        )
    } else {
        ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        )
    }

    OutlinedButton(
        onClick = onClick,
        shape = shape,
        colors = colorsToUse,
        modifier = modifier,
    ) {
        Text(
            text = text.toUpperCase(Locale.current),
        )
    }
}

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
                modifier = Modifier
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
        Surface(
            modifier = Modifier
                .padding(16.dp),
        ) {
            ToggleButtonRow(
                options = listOf(upcoming, past),
                selectedOption = upcoming,
            )
        }
    }
}
