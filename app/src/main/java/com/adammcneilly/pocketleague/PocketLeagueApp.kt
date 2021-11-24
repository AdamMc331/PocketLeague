package com.adammcneilly.pocketleague

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This is our custom implementation of the [Application] class. This will do any dependency injection
 * or third party initializations that must occur at app startup.
 */
@HiltAndroidApp
class PocketLeagueApp : Application()
