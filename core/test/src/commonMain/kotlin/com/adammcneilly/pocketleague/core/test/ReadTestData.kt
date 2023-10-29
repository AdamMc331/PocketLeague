package com.adammcneilly.pocketleague.core.test

import okio.FileSystem
import okio.Path.Companion.toPath

/**
 * Reads the test data from a file with the given [fileName].
 */
fun readTestData(
    fileName: String,
): String {
    val root = getEnv("TEST_DATA_ROOT")!!
    val fullPath = "$root/$fileName".toPath()

    return FileSystem.SYSTEM.read(fullPath) {
        readUtf8()
    }
}
