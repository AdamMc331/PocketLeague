package com.adammcneilly.pocketleague

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.saveable.rememberSaveable
import com.adammcneilly.pocketleague.navigation.AppNavHost
import com.adammcneilly.pocketleague.shared.ui.scaffold.app.App
import com.adammcneilly.pocketleague.shared.ui.scaffold.app.AppState

class MainActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?,
    ) {
        super.onCreate(savedInstanceState)

        setContent {
            enableEdgeToEdge()

            App()
        }
    }
}
