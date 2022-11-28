package com.adammcneilly.pocketleague.data.local.utils

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Convert a query of type [T] to a flow of a list of items of type [R].
 */
fun <T : Any, R> Query<T>.toListFlow(
    transform: (T) -> R,
): Flow<List<R>> {
    return this
        .asFlow()
        .mapToList()
        .map { queryList ->
            queryList.map(transform)
        }
}
