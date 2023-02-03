package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.MatchTeamResult

val TestModel.matchTeamResultWinner: MatchTeamResult
    get() = MatchTeamResult(
        score = 7,
        winner = true,
        team = TestModel.team,
        players = emptyList(),
        stats = null,
    )

val TestModel.matchTeamResultLoser: MatchTeamResult
    get() = matchTeamResultWinner.copy(
        score = 1,
        winner = false,
    )
