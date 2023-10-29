package com.adammcneilly.pocketleague.data.event.impl

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.data.event.api.EventListRequest
import com.adammcneilly.pocketleague.data.event.api.LocalEventService
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toEvents
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalEvent
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalEventStage
import com.adammcneilly.pocketleague.sqldelight.EventWithStage
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * An implementation of [LocalEventService] that requests and stores information
 * from the supplied [database].
 */
class SQLDelightEventService(
    private val database: PocketLeagueDB,
) : LocalEventService {
    override suspend fun insert(data: List<Event>) {
        database.transaction {
            data.forEach { event ->
                database
                    .localEventQueries
                    .insertFullEventObject(event.toLocalEvent())

                event.stages.forEach { stage ->
                    database
                        .localEventStageQueries
                        .insertFullEventStage(stage.toLocalEventStage(event.id))
                }
            }
        }
    }

    override fun stream(request: EventListRequest): Flow<List<Event>> {
        val eventQueries = database.localEventQueries

        val selectQuery =
            when (request) {
                is EventListRequest.AfterDate -> {
                    eventQueries.selectAfterDate(request.dateUtc)
                }
                is EventListRequest.Id -> {
                    eventQueries.selectById(request.eventId.id)
                }
                is EventListRequest.OnDate -> {
                    eventQueries.selectOnDate(request.dateUtc)
                }
            }

        return selectQuery
            .asFlow()
            .mapToList()
            .map(List<EventWithStage>::toEvents)
    }
}
