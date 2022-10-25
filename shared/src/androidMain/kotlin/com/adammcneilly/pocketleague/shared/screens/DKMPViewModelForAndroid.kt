package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.shared.data.Repository
import com.adammcneilly.pocketleague.shared.di.DataModule

/**
 * Creates an instance of a [DKMPViewModel] to be used within an Android application.
 */
fun DKMPViewModel.Factory.getAndroidInstance(
    dataModule: DataModule,
): DKMPViewModel {
    return DKMPViewModel(
        dependencies = Repository(
            dataModule = dataModule,
        ),
    )
}
