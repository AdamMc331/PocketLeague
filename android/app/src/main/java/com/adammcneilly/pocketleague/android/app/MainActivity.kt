package com.adammcneilly.pocketleague.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.adammcneilly.pocketleague.shared.app.PocketLeagueApp

/**
 * The main entry point into the application from the Android side.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?,
    ) {
        super.onCreate(savedInstanceState)

        setContent {
            enableEdgeToEdge()

            PocketLeagueApp()
        }
    }
}
