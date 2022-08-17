package com.adammcneilly.pocketleague.core.data

/**
 * This is a sealed class that represents the various states a request for data can be in.
 *
 * By wrapping a response into this single type, we can provide a way for asynchronous streams to
 * handle both success and failure scenarios, without having to catch exceptions. This is because any
 * exceptions will be wrapped inside an [Error] class.
 */
sealed class DataState<out T> {
    /**
     * This is used to imply we are still loading a piece of data.
     */
    object Loading : DataState<Nothing>()

    /**
     * A successful result which implies we will have some [data] returned.
     */
    data class Success<out T>(val data: T) : DataState<T>()

    /**
     * An unsuccessful result because some [error] occurred.
     */
    data class Error(val error: Throwable) : DataState<Nothing>()
}
