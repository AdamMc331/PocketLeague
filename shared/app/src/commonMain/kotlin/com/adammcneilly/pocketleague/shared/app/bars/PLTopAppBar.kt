package com.adammcneilly.pocketleague.shared.app.bars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Top app bar component that appears at the top of our application.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PLTopAppBar(
    text: String,
    showBack: Boolean,
    onBackClicked: () -> Unit,
    onDebugMenuClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = text,
            )
        },
        navigationIcon = {
            if (showBack) {
                IconButton(
                    onClick = onBackClicked,
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = onDebugMenuClicked,
            ) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "Debug Menu",
                )
            }
        },
        modifier = modifier,
    )
}
