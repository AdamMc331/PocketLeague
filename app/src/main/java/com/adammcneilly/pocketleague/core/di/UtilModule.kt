package com.adammcneilly.pocketleague.core.di

import android.content.Context
import com.adammcneilly.pocketleague.core.html.HTMLParser
import com.adammcneilly.pocketleague.core.html.JSoupHTMLParser
import com.adammcneilly.pocketleague.core.ui.FlagKitFlagResProvider
import com.adammcneilly.pocketleague.core.ui.FlagResProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Provides a number of dependencies that are related to utility classes.
 */
@Module
@InstallIn(SingletonComponent::class)
@Suppress("UndocumentedPublicFunction")
object UtilModule {

    @Provides
    fun provideFlagResProvider(
        @ApplicationContext applicationContext: Context,
    ): FlagResProvider {
        return FlagKitFlagResProvider(applicationContext)
    }

    @Provides
    fun provideHTMLParser(): HTMLParser {
        return JSoupHTMLParser()
    }
}