package com.adammcneilly.pocketleague.core.di

import com.adammcneilly.pocketleague.BuildConfig
import com.adammcneilly.pocketleague.core.data.remote.liquipedia.LiquipediaRetrofitAPI
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

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideLiquipediaAPI(
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
}
