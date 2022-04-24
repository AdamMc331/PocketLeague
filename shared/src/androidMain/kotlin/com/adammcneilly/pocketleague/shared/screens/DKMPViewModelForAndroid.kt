package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.shared.data.AppDependencies

/**
 * Creates an instance of a [DKMPViewModel] to be used within an Android application.
 */
fun DKMPViewModel.Factory.getAndroidInstance(): DKMPViewModel {
    return DKMPViewModel(
        dependencies = AppDependencies(),
    )
}
