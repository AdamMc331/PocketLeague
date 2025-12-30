package com.adammcneilly.pocketleague.shared

import androidx.compose.ui.window.ComposeUIViewController
import com.adammcneilly.pocketleague.shared.ui.scaffold.app.App

@Suppress("ktlint:standard:function-naming", "FunctionNaming")
fun MainViewController() =
    ComposeUIViewController {
        App()
    }
