package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.data.remote.RemoteParams

private fun EventListRequest.toOctaneParams(): RemoteParams {
    return mapOf(
        "group" to group,
        "tier" to tiers,
        "after" to after.toString(),
        "before" to before.toString(),
        "date" to date.toString(),
        "name" to name,
    )
}
