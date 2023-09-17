package com.adammcneilly.pocketleague.core.data

/**
 * Defines the data layer for requesting information
 * from a remote service.
 */
interface RemoteDataService<Request, Data> {

    /**
     * Makes an asynchronous request for [Data] for a given [request].
     */
    suspend fun fetch(request: Request): Result<Data>
}
