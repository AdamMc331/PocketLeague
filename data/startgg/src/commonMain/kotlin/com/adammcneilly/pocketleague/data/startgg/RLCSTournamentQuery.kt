package com.adammcneilly.pocketleague.data.startgg

import com.adammcneilly.pocketleague.data.startgg.type.TournamentPageFilter
import com.apollographql.apollo3.api.Optional

private const val ROCKET_LEAGUE_VIDEO_GAME_ID = "14"

/**
 * Every time we query for a list of tournaments that are RLCS tournaments, the filters here need to be applied.
 * Since Apollo generates data classes for their types, we can just call the copy method on this to provide
 * any additional filtering.
 */
val RLCSTournamentFilter =
    TournamentPageFilter(
        name = Optional.present("RLCS"),
        videogameIds = Optional.present(listOf(ROCKET_LEAGUE_VIDEO_GAME_ID)),
    )
