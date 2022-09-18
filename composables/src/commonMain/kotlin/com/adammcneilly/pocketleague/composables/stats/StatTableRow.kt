package com.adammcneilly.pocketleague.composables.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * Shows a row of stats.
 *
 * @param[title] The leading cell in this row that has a larger weight than the rest.
 * @param[cells] The individual stats to show within this row.
 * @param[boldCells] Defaults to false, but can be passed as true if we want to bold the text,
 * for a title row for example.
 * @param[textStyle] The [TextStyle] to apply to each cell. By default we use the local text style,
 * but we can override this if necessary.
 */
@Composable
fun StatTableRow(
    title: String,
    cells: List<String>,
    boldCells: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    val fontWeight: FontWeight? = if (boldCells) {
        FontWeight.Bold
    } else {
        null
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        TitleCell(
            title = title,
            fontWeight = fontWeight,
            textStyle = textStyle,
        )

        cells.forEach { stat ->
            StatCell(
                text = stat,
                fontWeight = fontWeight,
                textStyle = textStyle,
            )
        }
    }
}

@Composable
private fun RowScope.TitleCell(
    title: String,
    fontWeight: FontWeight?,
    textStyle: TextStyle,
) {
    Text(
        text = title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = fontWeight,
        modifier = Modifier.Companion
            .weight(2F),
        style = textStyle,
    )
}

@Composable
private fun RowScope.StatCell(
    text: String,
    fontWeight: FontWeight?,
    textStyle: TextStyle,
) {
    Text(
        text = text,
        textAlign = TextAlign.End,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = fontWeight,
        modifier = Modifier
            .weight(1F),
        style = textStyle,
    )
}
