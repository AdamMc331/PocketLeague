package com.adammcneilly.pocketleague.shared.app.debug

import okio.internal.commonToUtf8String
import org.jetbrains.compose.resources.ExperimentalResourceApi
import pocketleague.shared.app.generated.resources.Res

/**
 * Reads the data from a file with the given [fileName].
 */
@OptIn(ExperimentalResourceApi::class)
suspend fun readFile(
    fileName: String,
): String {
    val readBytes = Res.readBytes(fileName)
    return readBytes.commonToUtf8String()
}
