package com.adammcneilly.pocketleague.shared

/**
 * Creates an instance of a [DKMPViewModel] for iOS.
 */
fun DKMPViewModel.Factory.getIOSInstance(): DKMPViewModel {
    return DKMPViewModel(
        repository = Repository(),
    )
}
