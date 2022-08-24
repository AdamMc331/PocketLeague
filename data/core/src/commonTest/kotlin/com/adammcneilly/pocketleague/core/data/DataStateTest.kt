package com.adammcneilly.pocketleague.core.data

import kotlin.test.Test
import kotlin.test.assertEquals

class DataStateTest {

    @Test
    fun mapLoading() {
        val loading: DataState<Int> = DataState.Loading
        val mapped: DataState<String> = loading.map {
            ""
        }

        assertEquals(
            expected = DataState.Loading,
            actual = mapped,
        )
    }

    @Test
    fun mapError() {
        val error: DataState<Int> = DataState.Error(Throwable("Whoops"))
        val mapped: DataState<String> = error.map {
            ""
        }

        assertEquals(
            expected = "Whoops",
            actual = (mapped as DataState.Error).error.message,
        )
    }

    @Test
    fun mapSuccess() {
        val success: DataState<Int> = DataState.Success(1)
        val mapped: DataState<String> = success.map {
            it.toString()
        }

        assertEquals(
            expected = DataState.Success("1"),
            actual = mapped,
        )
    }
}
