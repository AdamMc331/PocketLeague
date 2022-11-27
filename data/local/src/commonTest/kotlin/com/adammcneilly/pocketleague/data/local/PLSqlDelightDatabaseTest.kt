package com.adammcneilly.pocketleague.data.local

import app.cash.turbine.test
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.team
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PLSqlDelightDatabaseTest {

    private val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    private val database = PLSqlDelightDatabase(driver)

    @Before
    fun before() {
        PocketLeagueDB.Schema.create(driver)
    }

    @Test
    fun readWriteTeams() = runBlocking {
        val testTeam = TestModel.team

        database.storeTeams(listOf(testTeam))

        database.getAllTeams()
            .test {
                val teamList = awaitItem()
                assertEquals(listOf(testTeam), teamList)
            }
    }
}
