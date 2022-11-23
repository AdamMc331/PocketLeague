package com.adammcneilly.pocketleague

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class PocketLeagueTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestPocketLeagueApp::class.java.canonicalName, context)
    }
}
