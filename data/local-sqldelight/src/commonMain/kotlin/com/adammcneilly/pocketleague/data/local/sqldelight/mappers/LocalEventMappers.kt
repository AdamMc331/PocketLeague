package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Prize
import com.adammcneilly.pocketleague.sqldelight.LocalEvent

@Deprecated("Use EventWithStage instead.")
fun LocalEvent.toEvent(): Event {
    return Event(
        id = Event.Id(this.id),
        name = this.name,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        imageURL = this.imageURL,
        // We pass an empty list here, and when we combine the data
        // in the data layer we'll get the stages from that.
        stages = emptyList(),
        tier = EventTier.valueOf(this.tier),
        mode = this.mode,
        region = EventRegion.valueOf(this.region),
        lan = this.lan,
        prize = if (this.prizeAmount != null && this.prizeCurrency != null) {
            Prize(
                amount = this.prizeAmount,
                currency = prizeCurrency,
            )
        } else {
            null
        },
    )
}

fun Event.toLocalEvent(): LocalEvent {
    return LocalEvent(
        id = this.id.id,
        name = this.name,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        imageURL = this.imageURL,
        tier = this.tier.name,
        mode = this.mode,
        region = this.region.name,
        lan = this.lan,
        prizeAmount = this.prize?.amount,
        prizeCurrency = this.prize?.currency,
    )
}
