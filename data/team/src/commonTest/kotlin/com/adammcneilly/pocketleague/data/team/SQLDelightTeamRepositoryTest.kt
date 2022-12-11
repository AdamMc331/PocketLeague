package com.adammcneilly.pocketleague.data.team

import app.cash.turbine.test
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.team
import com.adammcneilly.pocketleague.data.local.PocketLeagueDB
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SQLDelightTeamRepositoryTest {
    private lateinit var repository: SQLDelightTeamRepository

    @Before
    fun setUp() {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        PocketLeagueDB.Schema.create(driver)
        val database = PocketLeagueDB(driver)

        repository = SQLDelightTeamRepository(database)
    }

    @Test
    fun insertReadFavoriteTeam() = runTest {
        val favoriteTeam = TestModel.team.copy(
            isFavorite = true,
        )

        val insertedList = listOf(favoriteTeam)

        repository.insertTeams(insertedList)

        repository
            .getFavoriteTeams()
            .test {
                assertEquals(
                    insertedList,
                    awaitItem()
                )
            }
    }

    @Test
    fun insertReadNonFavoriteTeam() = runTest {
        val nonFavoriteTeam = TestModel.team.copy(
            isFavorite = false,
        )

        val insertedList = listOf(nonFavoriteTeam)

        repository.insertTeams(insertedList)

        repository
            .getFavoriteTeams()
            .test {
                assertEquals(
                    emptyList(),
                    awaitItem()
                )
            }
    }

    @Test
    fun insertReadActiveTeams() = runTest {
        val teamList = List(3) {
            TestModel.team.copy(
                id = it.toString(),
            )
        }

        repository.insertTeams(teamList)

        repository
            .getActiveRLCSTeams()
            .test {
                assertEquals(
                    teamList,
                    awaitItem()
                )
            }
    }
}
