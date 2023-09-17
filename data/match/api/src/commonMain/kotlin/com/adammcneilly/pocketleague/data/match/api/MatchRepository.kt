package com.adammcneilly.pocketleague.data.match.api

import com.adammcneilly.pocketleague.core.data.Repository
import com.adammcneilly.pocketleague.core.models.Match

/**
 * Defines the data layer for any match related requests.
 */
interface MatchRepository : Repository<MatchListRequest, List<Match>>
