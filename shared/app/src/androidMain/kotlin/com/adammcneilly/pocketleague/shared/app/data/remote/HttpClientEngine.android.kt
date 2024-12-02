package com.adammcneilly.pocketleague.shared.app.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual fun httpClientEngine(): HttpClientEngine {
    return Android.create()
}
