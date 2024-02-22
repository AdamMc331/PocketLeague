package com.adammcneilly.pocketleague.shared.app.swiss

import com.adammcneilly.pocketleague.core.models.SwissTeamResult
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.startgg.PhaseGroupQuery
import com.adammcneilly.pocketleague.data.startgg.StartGGApolloClient
import com.adammcneilly.pocketleague.data.startgg.mappers.toTeam
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional

class StartGGSwissStageRepository(
    private val apiClient: ApolloClient = StartGGApolloClient,
) : SwissStageRepository {
    override suspend fun fetchSwissStageResults(
        stageId: String,
    ): Result<List<SwissTeamResult>> {
        val query = PhaseGroupQuery(
            id = Optional.present(stageId),
        )

        val data = apiClient.query(query).execute().data

        return if (data != null) {
            val gameRecords = data.phaseGroup?.sets?.let { sets ->
                parseGameWinLossRecords(sets)
            }

            val resultList: List<SwissTeamResult> = data.phaseGroup?.standings?.nodes?.map { node ->
                val team = node?.entrant?.team?.teamFragment?.toTeam() ?: Team()

                SwissTeamResult(
                    team = team,
                    matchWins = gameRecords?.matchWinMap?.get(team.id) ?: 0,
                    matchLosses = gameRecords?.matchLossMap?.get(team.id) ?: 0,
                    gameWins = gameRecords?.gameWinMap?.get(team.id) ?: 0,
                    gameLosses = gameRecords?.gameLossMap?.get(team.id) ?: 0,
                )
            }.orEmpty()

            Result.success(resultList)
        } else {
            Result.failure(Throwable("Unable to fetch phase group for: $stageId"))
        }
    }

    data class GameWinLossRecords(
        val gameWinMap: Map<String, Int>,
        val gameLossMap: Map<String, Int>,
        val matchWinMap: Map<String, Int>,
        val matchLossMap: Map<String, Int>,
    )

    private fun parseGameWinLossRecords(
        sets: PhaseGroupQuery.Sets,
    ): GameWinLossRecords {
        val matchWinMap: MutableMap<String, Int> = mutableMapOf()
        val matchLossMap: MutableMap<String, Int> = mutableMapOf()
        val gameWinMap: MutableMap<String, Int> = mutableMapOf()
        val gameLossMap: MutableMap<String, Int> = mutableMapOf()

        sets.nodes?.forEach { node ->
            val entrant1EntrantId = node?.slots?.getOrNull(0)?.entrant?.id
            val entrant1TeamId = node?.slots?.getOrNull(0)?.entrant?.team?.teamFragment?.id.orEmpty()
            val entrant2TeamId = node?.slots?.getOrNull(1)?.entrant?.team?.teamFragment?.id.orEmpty()
            val winnerId = node?.winnerId ?: 0

            node?.games?.forEach { game ->
                val entrant1Score = game?.entrant1Score
                val entrant2Score = game?.entrant2Score

                if (entrant1Score != null && entrant2Score != null) {
                    val entrant1Wins = (entrant1Score > entrant2Score)

                    if (entrant1Wins) {
                        gameWinMap.setOrIncrement(entrant1TeamId)
                        gameLossMap.setOrIncrement(entrant2TeamId)
                    } else {
                        gameWinMap.setOrIncrement(entrant2TeamId)
                        gameLossMap.setOrIncrement(entrant1TeamId)
                    }
                }
            }

            if (winnerId.toString() == entrant1EntrantId) {
                matchWinMap.setOrIncrement(entrant1TeamId)
                matchLossMap.setOrIncrement(entrant2TeamId)
            } else {
                matchWinMap.setOrIncrement(entrant2TeamId)
                matchLossMap.setOrIncrement(entrant1TeamId)
            }
        }

        return GameWinLossRecords(
            matchWinMap = matchWinMap,
            matchLossMap = matchLossMap,
            gameWinMap = gameWinMap,
            gameLossMap = gameLossMap,
        )
    }
}

private fun MutableMap<String, Int>.setOrIncrement(
    key: String,
) {
    val currentValue = this[key] ?: 0
    this[key] = currentValue.inc()
}
