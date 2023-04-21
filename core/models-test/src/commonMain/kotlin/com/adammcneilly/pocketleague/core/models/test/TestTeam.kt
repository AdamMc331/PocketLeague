package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.Team

val TestModel.team: Team
    get() = Team(
        id = "12345",
        name = "Knights",
        lightThemeImageURL = "teamImageURL",
    )
