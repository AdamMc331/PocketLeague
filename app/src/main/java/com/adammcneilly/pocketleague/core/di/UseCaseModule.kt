package com.adammcneilly.pocketleague.core.di

import com.adammcneilly.pocketleague.event.implementation.GetEventOverviewUseCaseImpl
import com.adammcneilly.pocketleague.event.implementation.GetEventSummariesUseCaseImpl
import com.adammcneilly.pocketleague.shared.data.event.EventRepository
import com.adammcneilly.pocketleague.shared.eventoverview.domain.GetEventOverviewUseCase
import com.adammcneilly.pocketleague.shared.eventsummarylist.domain.GetEventSummariesUseCase
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
        ): GetEventSummariesUseCase {
            return GetEventSummariesUseCaseImpl(
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
