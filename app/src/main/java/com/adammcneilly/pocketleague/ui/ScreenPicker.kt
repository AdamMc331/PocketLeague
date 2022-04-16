package com.adammcneilly.pocketleague.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.Screen
import com.adammcneilly.pocketleague.shared.screens.ScreenIdentifier

@Composable
fun Navigation.ScreenPicker(
    screenIdentifier: ScreenIdentifier
) {

    when (screenIdentifier.screen) {
        Screen.Feed -> {
            Text(text = "Feed Screen")
        }
    }
}
