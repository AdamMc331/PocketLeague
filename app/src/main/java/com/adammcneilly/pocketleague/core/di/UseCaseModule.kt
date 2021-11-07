package com.adammcneilly.pocketleague.core.di

import com.adammcneilly.pocketleague.teamlist.domain.usecases.FetchAllTeamsUseCase
import com.adammcneilly.pocketleague.teamlist.domain.usecases.FetchAllTeamsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindFetchAllTeamsUseCase(
        fetchAllTeamsUseCase: FetchAllTeamsUseCaseImpl
    ): FetchAllTeamsUseCase
}
