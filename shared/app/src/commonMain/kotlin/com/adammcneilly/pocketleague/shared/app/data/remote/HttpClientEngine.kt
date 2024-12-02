package com.adammcneilly.pocketleague.shared.app.data.remote

import io.ktor.client.engine.HttpClientEngine

expect fun httpClientEngine(): HttpClientEngine
