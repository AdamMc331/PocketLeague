package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.shared.app.feature.feed.FeedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        FeedViewModel(
            getPastWeeksMatchesUseCase = get(),
            getOngoingEventsUseCase = get(),
            getUpcomingEventsUseCase = get(),
        )
    }
}
