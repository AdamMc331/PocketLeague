package com.adammcneilly.pocketleague.core.data

import kotlinx.coroutines.flow.Flow

/**
 * Interface defining functions for inserting and streaming data
 * from a local data source.
 */
interface LocalDataService<Request, Data> {
    /**
     * Insert the supplied [data] to our local data source.
     */
    suspend fun insert(
        data: Data,
    )

    /**
     * Stream a flow of [Data] for the given [request].
     */
    fun stream(
        request: Request,
    ): Flow<Data>
}
