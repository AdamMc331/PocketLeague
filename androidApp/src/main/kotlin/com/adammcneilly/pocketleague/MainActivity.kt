package com.adammcneilly.pocketleague

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
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

            // Commented out because I can't figure out the parcelable KMP stuff
            // yet.
//            val appState = rememberSaveable(saver = AppState.saver) {
            val appState = remember {
                AppState()
            }

            App(
                appState = appState,
            ) {
                AppNavHost()
            }
        }
    }
}
