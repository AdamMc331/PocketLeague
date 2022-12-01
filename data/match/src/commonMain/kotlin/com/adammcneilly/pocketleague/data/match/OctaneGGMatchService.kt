package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.data.remote.RemoteParams

private fun MatchListRequest.toOctaneParams(): RemoteParams {
    return mapOf(
        "after" to this.after.toString(),
        "before" to this.before.toString(),
        "group" to this.group,
        "event" to this.eventId,
        "stage" to this.stageId,
        "region" to this.region,
        "team" to this.team,
        "sort" to this.sort,
    )
}
