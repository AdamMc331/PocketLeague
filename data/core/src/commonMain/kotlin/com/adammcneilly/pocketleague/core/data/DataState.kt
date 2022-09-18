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

    /**
     * Converts a [DataState] of type [T] to one of type [R] using the supplied [mapper] function.
     */
    fun <R> map(mapper: (T) -> R): DataState<R> {
        return when (this) {
            Loading -> Loading
            is Success -> {
                try {
                    val mappedData = mapper(this.data)
                    Success(mappedData)
                } catch (error: Throwable) {
                    // ARM - In the future, we should log this
                    // mapping error, maybe even find a way to ignore
                    // items that can't be mapped.
                    Error(error)
                }
            }
            is Error -> this
        }
    }
}
