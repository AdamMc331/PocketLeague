package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.shared.data.Repository

/**
 * Creates an instance of a [DKMPViewModel] to be used within an Android application.
 */
fun DKMPViewModel.Factory.getAndroidInstance(): DKMPViewModel {
    return DKMPViewModel(
        repository = Repository(),
    )
}
