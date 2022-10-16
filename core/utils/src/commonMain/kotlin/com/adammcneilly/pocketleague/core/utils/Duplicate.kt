package com.adammcneilly.pocketleague.core.utils

/**
 * Given some [item], convert it into a list of size [count].
 */
inline fun <T> T.duplicate(count: Int): List<T> {
    return (1..count).map {
        this
    }
}
