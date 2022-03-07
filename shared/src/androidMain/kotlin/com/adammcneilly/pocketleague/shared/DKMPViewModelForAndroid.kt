package com.adammcneilly.pocketleague.shared

/**
 * Create an instance of a [DKMPViewModel] for Android.
 */
fun DKMPViewModel.Factory.getAndroidInstance(): DKMPViewModel {
    return DKMPViewModel(
        repository = Repository(),
    )
}
