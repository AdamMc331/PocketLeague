package com.adammcneilly.pocketleague.core.data.remote.octanegg.services

import app.cash.turbine.test
import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.EventListRequest
import com.adammcneilly.pocketleague.core.data.remote.buildTestHTTPClient
import com.adammcneilly.pocketleague.core.data.remote.eventListResponseJson
import com.adammcneilly.pocketleague.core.data.remote.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.core.models.Event
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class OctaneGGEventServiceTest {

    @Test
    fun fetchEvents() = runTest {
        val mockEngine = MockEngine { request ->
            println("URL: ${request.url}")

            respond(
                content = ByteReadChannel(eventListResponseJson),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val apiClient = OctaneGGAPIClient(
            httpClient = buildTestHTTPClient(mockEngine)
        )

        val service = OctaneGGEventService(apiClient)

        val request = EventListRequest()

        withContext(Dispatchers.Default) {
            service.fetchEvents(request).test {
                val loadingState = awaitItem() as? DataState.Loading
                assertNotNull(loadingState)

                val successState = awaitItem() as? DataState.Success<List<Event>>
                assertNotNull(successState)
                assertTrue(successState.data.size == 6)

                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
