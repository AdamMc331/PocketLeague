package com.adammcneilly.pocketleague.core.test

/**
 * Retrieve an environment variable with a given [name].
 */
internal expect fun getEnv(name: String): String?
