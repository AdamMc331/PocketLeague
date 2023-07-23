package com.adammcneilly.pocketleague.data.game

internal actual fun getEnv(name: String): String? =
    System.getenv(name)
