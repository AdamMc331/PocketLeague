package com.adammcneilly.pocketleague.data.event

import app.cash.turbine.test
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.data.local.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.mappers.toLocalEvent
import com.adammcneilly.pocketleague.data.remote.test.FakeKTORClient
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class OfflineFirstEventServiceTest {

    @Test
    fun observeAndStoreEventParticipants() = runTest {
        val testEvent = TestModel.event

        // Setup dependencies
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        PocketLeagueDB.Schema.create(driver)
        val inMemoryDatabase = PocketLeagueDB(driver)

        val participantsUrl = "/events/${testEvent.id}/participants"
        val fakeApiClient = FakeKTORClient(
            mockResponses = mapOf(
                participantsUrl to OctaneParticipantsListResponse.json,
            ),
        )

        // Insert test event
        inMemoryDatabase
            .localEventQueries
            .insertFullEventObject(testEvent.toLocalEvent())

        val service = OfflineFirstEventService(
            database = inMemoryDatabase,
            apiClient = fakeApiClient,
        )

        service
            .getEventParticipants(testEvent.id)
            .test {
                val teams = awaitItem()

                assertEquals(
                    expected = 13,
                    actual = teams.size,
                )
            }
    }
}
