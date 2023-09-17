package com.adammcneilly.pocketleague.data.match.api

import com.adammcneilly.pocketleague.core.data.RemoteDataService
import com.adammcneilly.pocketleague.core.models.Match

/**
 * Defines a data contract for requesting necessary remote
 * information about matches.
 */
interface RemoteMatchService : RemoteDataService<MatchListRequest, List<Match>>
