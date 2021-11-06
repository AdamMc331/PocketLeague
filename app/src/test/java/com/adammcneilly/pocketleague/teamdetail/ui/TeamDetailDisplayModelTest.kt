package com.adammcneilly.pocketleague.teamdetail.ui

import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.core.ui.FakeFlagResProvider
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.player.ui.toDisplayModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TeamDetailDisplayModelTest {

    @Test
    fun createFromTeam() {
        val countryCode = "US"
        val flagResProvider = FakeFlagResProvider()
        val mockFlagRes = 123
        flagResProvider.mockFlagResForCountry(countryCode, mockFlagRes)

        val player = Player(
            countryCode = countryCode,
            gamerTag = "Testy",
            realName = "McTestFace",
            notes = null,
        )

        val team = Team(
            name = "PK",
            logoImageUrl = "LogoURL",
            roster = listOf(player),
        )

        val displayModel = team.toDetailDisplayModel(flagResProvider)

        with(displayModel) {
            assertThat(name).isEqualTo(team.name)
            assertThat(logo).isEqualTo(UIImage.Remote(team.logoImageUrl))
            assertThat(players).isEqualTo(listOf(player.toDisplayModel(flagResProvider)))
        }
    }
}
