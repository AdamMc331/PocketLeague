package com.adammcneilly.pocketleague.core.test

internal actual fun getEnv(name: String): String? = System.getenv(name)
