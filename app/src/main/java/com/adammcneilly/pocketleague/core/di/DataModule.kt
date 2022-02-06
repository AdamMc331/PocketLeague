package com.adammcneilly.pocketleague.core.di

import com.adammcneilly.pocketleague.event.data.EventService
import com.adammcneilly.pocketleague.event.data.remote.SmashGGEventService
import com.adammcneilly.pocketleague.phase.data.PhaseService
import com.adammcneilly.pocketleague.phase.data.remote.SmashGGPhaseService
import com.adammcneilly.pocketleague.teamlist.data.MockTeamListService
import com.adammcneilly.pocketleague.teamlist.data.TeamListService
import dagger.Binds
import dagger.Module
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

    @Binds
    abstract fun bindEventService(
        eventService: SmashGGEventService,
    ): EventService

    @Binds
    abstract fun bindPhaseService(
        phaseService: SmashGGPhaseService,
    ): PhaseService
}
