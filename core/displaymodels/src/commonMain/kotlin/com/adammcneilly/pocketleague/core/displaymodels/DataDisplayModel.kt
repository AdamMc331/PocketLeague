package com.adammcneilly.pocketleague.core.displaymodels

/**
 * A sealed class of all different ways a piece of data can be displayed in the UI.
 */
sealed class DataDisplayModel<out T> {

    /**
     * This is used when a piece of data is being requested, and is not yet available.
     */
    object Loading : DataDisplayModel<Nothing>()

    /**
     * This is used once a piece of [content] has been successfully requested.
     */
    data class Content<T>(
        val content: T,
    ) : DataDisplayModel<T>()

    /**
     * This is used when a failure happened while requesting a piece of data.
     */
    data class Error(
        val errorMessage: String,
    ) : DataDisplayModel<Nothing>()
}
