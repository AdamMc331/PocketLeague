package com.adammcneilly.pocketleague.data.local.sqldelight.util

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Converts a query for some [LocalType] to a flow of a single [DomainType] entity.
 */
@Suppress("ktlint:standard:function-signature")
fun <LocalType : Any, DomainType> Query<LocalType>.asFlowSingle(
    transform: (LocalType) -> DomainType,
): Flow<DomainType> {
    return this
        .asFlow()
        .mapToOne()
        .map(transform)
}
