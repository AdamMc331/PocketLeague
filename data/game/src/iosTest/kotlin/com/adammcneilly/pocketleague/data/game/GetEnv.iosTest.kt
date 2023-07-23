package com.adammcneilly.pocketleague.data.game

import kotlinx.cinterop.toKString
import platform.posix.getenv

internal actual fun getEnv(name: String): String? =
    getenv(name)?.toKString()
