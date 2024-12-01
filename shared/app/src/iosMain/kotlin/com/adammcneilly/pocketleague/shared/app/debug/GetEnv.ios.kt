package com.adammcneilly.pocketleague.shared.app.debug

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import platform.posix.getenv

@OptIn(ExperimentalForeignApi::class)
actual fun getEnv(
    name: String,
): String? = getenv(name)?.toKString()
