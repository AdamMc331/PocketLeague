package com.adammcneilly.pocketleague.shared.app.debug

actual fun getEnv(
    name: String,
): String? = System.getenv(name)
