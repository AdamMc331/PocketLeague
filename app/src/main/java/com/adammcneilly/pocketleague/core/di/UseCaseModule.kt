package com.adammcneilly.pocketleague.core.di

import com.adammcneilly.pocketleague.eventsummary.domain.usecases.GetUpcomingEventsUseCase
import com.adammcneilly.pocketleague.eventsummary.domain.usecases.GetUpcomingEventsUseCaseImpl
import com.adammcneilly.pocketleague.teamlist.domain.usecases.FetchAllTeamsUseCase
import com.adammcneilly.pocketleague.teamlist.domain.usecases.FetchAllTeamsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * A collection of all use case dependencies in the application.
 */
@Module
@InstallIn(SingletonComponent::class)
@Suppress("UndocumentedPublicFunction")
abstract class UseCaseModule {
    @Binds
    abstract fun bindFetchAllTeamsUseCase(
        fetchAllTeamsUseCase: FetchAllTeamsUseCaseImpl
    ): FetchAllTeamsUseCase

    @Binds
    abstract fun bindGetUpcomingEventsUseCase(
        getUpcomingEventsUseCase: GetUpcomingEventsUseCaseImpl
    ): GetUpcomingEventsUseCase
}
