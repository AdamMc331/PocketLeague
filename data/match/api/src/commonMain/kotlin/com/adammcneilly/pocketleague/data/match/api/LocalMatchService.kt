package com.adammcneilly.pocketleague.data.match.api

import com.adammcneilly.pocketleague.core.data.LocalDataService
import com.adammcneilly.pocketleague.core.models.Match

/**
 * Represents a local source of truth for the match domain.
 */
interface LocalMatchService : LocalDataService<MatchListRequest, List<Match>>
