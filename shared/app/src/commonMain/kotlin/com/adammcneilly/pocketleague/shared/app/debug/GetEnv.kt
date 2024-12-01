package com.adammcneilly.pocketleague.shared.app.debug

/**
 * Retrieve an environment variable with a given [name].
 */
expect fun getEnv(
    name: String,
): String?
