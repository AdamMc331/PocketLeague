package com.adammcneilly.pocketleague.shared.data

/**
 * This is a sealed class that represents two options for a data response, where the response is
 * either successful or a failure.
 *
 * By wrapping a response into this single type, we can provide a way for asynchronous streams to
 * handle both success and failure scenarios, without having to catch exceptions. This is because any
 * exceptions will be wrapped inside an [Error] class.
 */
sealed class DataResult<out T> {
    /**
     * A successful result which implies we will have some [data] returned.
     */
    data class Success<out T>(val data: T) : DataResult<T>()

    /**
     * An unsuccessful result because some [error] occurred.
     */
    data class Error(val error: Throwable) : DataResult<Nothing>()
}
