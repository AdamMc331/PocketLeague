package com.adammcneilly.pocketleague.data.local.util

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Converts a query for some [LocalType] to a flow of a list of [DomainType] entities.
 */
fun <LocalType : Any, DomainType> Query<LocalType>.asFlowList(
    transform: (LocalType) -> DomainType,
): Flow<List<DomainType>> {
    return this
        .asFlow()
        .mapToList()
        .map { localItemList ->
            localItemList.map(transform)
        }
}
