package com.adammcneilly.pocketleague.data.local.utils

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Convert a query of type [T] to a flow of an item of type [R].
 */
fun <T : Any, R> Query<T>.toSingleFlow(
    transform: (T) -> R,
): Flow<R> {
    return this
        .asFlow()
        .mapToOne()
        .map { queryItem ->
            transform.invoke(queryItem)
        }
}
