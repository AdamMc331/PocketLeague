package com.adammcneilly.pocketleague.core.di

import com.adammcneilly.pocketleague.event.api.EventRepository
import com.adammcneilly.pocketleague.event.api.GetEventOverviewUseCase
import com.adammcneilly.pocketleague.event.api.GetUpcomingEventSummariesUseCase
import com.adammcneilly.pocketleague.event.implementation.GetEventOverviewUseCaseImpl
import com.adammcneilly.pocketleague.event.implementation.GetUpcomingEventSummariesUseCaseImpl
import com.adammcneilly.pocketleague.teamlist.domain.usecases.FetchAllTeamsUseCase
import com.adammcneilly.pocketleague.teamlist.domain.usecases.FetchAllTeamsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
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
        fetchAllTeamsUseCase: FetchAllTeamsUseCaseImpl,
    ): FetchAllTeamsUseCase

    companion object {
        @Provides
        fun provideGetUpcomingEventSummariesUseCase(
            repository: EventRepository,
        ): GetUpcomingEventSummariesUseCase {
            return GetUpcomingEventSummariesUseCaseImpl(
                repository = repository,
            )
        }

        @Provides
        fun provideGetEventOverviewUseCase(
            repository: EventRepository,
        ): GetEventOverviewUseCase {
            return GetEventOverviewUseCaseImpl(
                repository = repository,
            )
        }
    }
}
