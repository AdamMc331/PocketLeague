package com.adammcneilly.pocketleague.feature.debugmenu

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant

/**
 * A custom text field looking composable that displays a date. Clicking on it, will allow the user
 * to select a new date by launching a [DatePickerDialog].
 *
 * In the future, we will want to support a disabled state using the supplied [enabled] boolean.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PLDatePickerInput(
    value: Instant,
    onValueChanged: (Instant) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = value.toEpochMilliseconds(),
    )

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                OkButton(
                    onClick = {
                        val newInstant = datePickerState.selectedDateMillis
                            ?.let(Instant.Companion::fromEpochMilliseconds)

                        if (newInstant != null) {
                            onValueChanged.invoke(newInstant)
                        }

                        showDatePicker = false
                    },
                )
            },
        ) {
            DatePicker(
                state = datePickerState,
            )
        }
    }

    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(50),
                )
                .clip(RoundedCornerShape(50))
                .clickable {
                    showDatePicker = true
                },
        ) {
            DateAndIcon(
                value = value,
                textColor = MaterialTheme.colorScheme.onBackground,
                iconColorToUse = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
private fun OkButton(
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick,
    ) {
        Text(
            text = "OK",
        )
    }
}

@Composable
private fun DateAndIcon(
    value: Instant,
    textColor: Color,
    iconColorToUse: Color,
) {
    Row(
        modifier = Modifier
            .padding(16.dp),
    ) {
        Text(
            text = value.toString(),
            color = textColor,
            modifier = Modifier
                .weight(1F),
        )

        Icon(
            Icons.Default.DateRange,
            contentDescription = "Select Date",
            tint = iconColorToUse,
        )
    }
}
