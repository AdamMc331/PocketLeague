package com.adammcneilly.pocketleague.shared.app.core.di

import com.adammcneilly.pocketleague.shared.app.di.appModule
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify
import kotlin.test.Test

/**
 * This is currently JVM only, so it must exist here inside the androidUnitTest
 * source set.
 */
class AppModuleTest {
    @Test
    @OptIn(KoinExperimentalAPI::class)
    fun verifyKoinModule() {
        appModule.verify()
    }
}
