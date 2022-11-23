package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.Format

val TestModel.formatBO7: Format
    get() = Format(
        type = "best",
        length = 7,
    )
