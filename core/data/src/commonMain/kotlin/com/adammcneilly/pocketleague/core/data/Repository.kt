package com.adammcneilly.pocketleague.core.data

import kotlinx.coroutines.flow.Flow

/**
 * Defines the data layer for any data repository.
 */
interface Repository<Request, Data> {

    /**
     * Provide a flow response of data for the given [request].
     *
     * All data is provided from our source of truth, and refreshed unless
     * [refreshCache] is set to false. This is ideal for stale data that is unlikely to
     * have changed.
     */
    fun stream(
        request: Request,
        refreshCache: Boolean = true,
    ): Flow<Data>
}
