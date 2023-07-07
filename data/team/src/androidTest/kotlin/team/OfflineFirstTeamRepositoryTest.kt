// package team
//
// import app.cash.turbine.test
// import com.adammcneilly.pocketleague.core.models.test.TestModel
// import com.adammcneilly.pocketleague.core.models.test.team
// import com.adammcneilly.pocketleague.data.team.OfflineFirstTeamRepository
// import com.adammcneilly.pocketleague.data.team.test.FakeTeamRepository
// import kotlinx.coroutines.test.runTest
// import org.junit.Before
// import kotlin.test.Test
// import kotlin.test.assertEquals
//
// class OfflineFirstTeamRepositoryTest {
//
//    private lateinit var localDataSource: FakeTeamRepository
//    private lateinit var remoteDataSource: FakeTeamRepository
//    private lateinit var repository: OfflineFirstTeamRepository
//
//    @Before
//    fun setUp() {
//        localDataSource = FakeTeamRepository()
//        remoteDataSource = FakeTeamRepository()
//
//        repository = OfflineFirstTeamRepository(
//            localDataSource = localDataSource,
//            remoteDataSource = remoteDataSource,
//        )
//    }
//
//    @Test
//    fun getFavoriteTeamsOnlyReturnsLocalSource() = runTest {
//        val localFavoriteTeams = listOf(TestModel.team)
//        localDataSource.favoriteTeams = localFavoriteTeams
//
//        repository
//            .getFavoriteTeams()
//            .test {
//                assertEquals(
//                    localFavoriteTeams,
//                    awaitItem(),
//                )
//
//                awaitComplete()
//
//                assertEquals(emptyList(), localDataSource.insertedTeams)
//                assertEquals(emptyList(), remoteDataSource.insertedTeams)
//                assertEquals(0, remoteDataSource.favoriteTeamsRequestCount)
//            }
//    }
//
//    @Test
//    fun getActiveTeamsReturnsLocalAndFetchesRemote() = runTest {
//        val localActiveTeams = listOf(TestModel.team).map {
//            it.copy(name = "Local Team")
//        }
//
//        val remoteActiveTeams = localActiveTeams.map {
//            it.copy(name = "Remote Team")
//        }
//
//        localDataSource.activeTeams = localActiveTeams
//        remoteDataSource.activeTeams = remoteActiveTeams
//
//        repository
//            .getActiveRLCSTeams()
//            .test {
//                // Should return local teams
//                assertEquals(
//                    localActiveTeams,
//                    awaitItem(),
//                )
//
//                awaitComplete()
//
//                // Ensure remote teams got inserted to local
//                assertEquals(
//                    remoteActiveTeams,
//                    localDataSource.insertedTeams,
//                )
//
//                // Ensure remote insert call didn't happen
//                assertEquals(
//                    emptyList(),
//                    remoteDataSource.insertedTeams,
//                )
//            }
//    }
//
//    @Test
//    fun insertTeamsOnlyInsertsToLocal() = runTest {
//        val testTeams = listOf(TestModel.team)
//
//        repository.insertTeams(testTeams)
//
//        assertEquals(testTeams, localDataSource.insertedTeams)
//        assertEquals(emptyList(), remoteDataSource.insertedTeams)
//    }
//
//    @Test
//    fun updateFavoritesOnlyCallsLocal() = runTest {
//        val teamId = "1234"
//        val isFavorite = true
//
//        repository.updateIsFavorite(teamId, isFavorite)
//
//        localDataSource.verifyFavoriteStatus(teamId, isFavorite)
//        remoteDataSource.verifyFavoriteStatusNotUpdated(teamId)
//    }
// }
