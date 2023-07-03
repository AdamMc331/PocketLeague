package com.adammcneilly.pocketleague.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.adammcneilly.pocketleague.shared.app.PocketLeagueApp
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketLeagueTheme {
                PocketLeagueApp()
            }
        }
    }
}
