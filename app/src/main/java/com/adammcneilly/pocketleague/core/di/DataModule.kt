package com.adammcneilly.pocketleague.core.di

import com.adammcneilly.pocketleague.event.data.EventService
import com.adammcneilly.pocketleague.event.data.remote.LiquipediaEventService
import com.adammcneilly.pocketleague.teamlist.data.TeamListService
import com.adammcneilly.pocketleague.teamlist.data.remote.LiquipediaTeamListService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindTeamListService(
        teamListService: LiquipediaTeamListService,
    ): TeamListService

    @Binds
    abstract fun bindEventService(
        eventService: LiquipediaEventService,
    ): EventService
}
