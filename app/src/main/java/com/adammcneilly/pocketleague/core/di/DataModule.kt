package com.adammcneilly.pocketleague.core.di

import com.adammcneilly.pocketleague.shared.data.event.EventRepository
import com.adammcneilly.pocketleague.shared.data.event.remote.smashgg.SmashGGEventService
import com.adammcneilly.pocketleague.shared.data.phase.PhaseRepository
import com.adammcneilly.pocketleague.shared.data.phase.remote.smashgg.SmashGGPhaseService
import com.adammcneilly.pocketleague.teamlist.data.MockTeamListService
import com.adammcneilly.pocketleague.teamlist.data.TeamListService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Defines all dependencies related to our data layer.
 */
@Module
@InstallIn(SingletonComponent::class)
@Suppress("UndocumentedPublicFunction")
abstract class DataModule {
    @Binds
    abstract fun bindTeamListService(
        teamListService: MockTeamListService,
    ): TeamListService

    companion object {
        @Provides
        fun provideFeatureEventRepository(): com.adammcneilly.pocketleague.event.api.EventRepository {
            return com.adammcneilly.pocketleague.event.implementation.smashgg.SmashGGEventService()
        }

        @Provides
        fun provideEventRepository(): EventRepository {
            return SmashGGEventService()
        }

        @Provides
        fun providePhaseRepository(): PhaseRepository {
            return SmashGGPhaseService()
        }
    }
}
