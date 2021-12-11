package com.adammcneilly.pocketleague.core.di

import com.adammcneilly.pocketleague.BuildConfig
import com.adammcneilly.pocketleague.core.data.remote.liquipedia.LiquipediaRetrofitAPI
import com.adammcneilly.pocketleague.core.data.remote.smashgg.SmashGGAuthorizationInterceptor
import com.apollographql.apollo.ApolloClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier

/**
 * A [Qualifier] annotation for the Liquipedia OkHttp client dependency.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LiquipediaOkHttpClient

/**
 * A [Qualifier] annotation for the Smash.gg OkHttp client dependency.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SmashGGOkHttpClient

/**
 * A module to provide all dependencies related to remote data requests.
 */
@Module
@InstallIn(SingletonComponent::class)
@Suppress("UndocumentedPublicFunction")
object RemoteModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            val level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }

            setLevel(level)
        }
    }

    @LiquipediaOkHttpClient
    @Provides
    fun provideLiquipediaOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @SmashGGOkHttpClient
    @Provides
    fun provideSmashGGOkHttpClient(
        authInterceptor: SmashGGAuthorizationInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideLiquipediaAPI(
        @LiquipediaOkHttpClient
        client: OkHttpClient,
    ): LiquipediaRetrofitAPI {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("https://liquipedia.net/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(LiquipediaRetrofitAPI::class.java)
    }

    @Provides
    fun provideSmashGGApolloClient(
        @SmashGGOkHttpClient
        client: OkHttpClient,
    ): ApolloClient {
        return ApolloClient.builder()
            .serverUrl("https://api.smash.gg/gql/alpha")
            .okHttpClient(client)
            .build()
    }
}
