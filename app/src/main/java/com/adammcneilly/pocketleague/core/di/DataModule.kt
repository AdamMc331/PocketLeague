package com.adammcneilly.pocketleague.core.di

import com.adammcneilly.pocketleague.teamlist.data.DemoTeamListService
import com.adammcneilly.pocketleague.teamlist.data.TeamListService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindTeamListService(
        teamListService: DemoTeamListService,
    ): TeamListService
}
