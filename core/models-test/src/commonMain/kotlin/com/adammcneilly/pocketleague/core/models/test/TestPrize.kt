package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.Prize

val TestModel.prize: Prize
    get() = Prize(
        amount = 2085000.0,
        currency = "USD",
    )
