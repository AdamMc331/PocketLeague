package com.adammcneilly.pocketleague

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.adammcneilly.pocketleague.shared.App

fun main() =
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KotlinProject",
        ) {
            App()
        }
    }
