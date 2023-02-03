package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.shared.di.AppModule

/**
 * Creates an instance of a [DKMPViewModel] to be used within an Android application.
 */
fun DKMPViewModel.Factory.getAndroidInstance(
    appModule: AppModule
): DKMPViewModel {
    return DKMPViewModel(
        appModule = appModule
    )
}
