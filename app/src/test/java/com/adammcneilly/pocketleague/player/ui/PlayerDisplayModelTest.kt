package com.adammcneilly.pocketleague.player.ui

import com.adammcneilly.pocketleague.core.ui.FakeFlagResProvider
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.shared.core.models.Player
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PlayerDisplayModelTest {

    @Test
    fun createFromPlayerWithoutNotes() {
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

        val displayModel = player.toDisplayModel(flagResProvider)

        with(displayModel) {
            assertThat(flagImage).isEqualTo(UIImage.AndroidResource(mockFlagRes))
            assertThat(gamerTag).isEqualTo("Testy")
            assertThat(realName).isEqualTo("(McTestFace)")
            assertThat(notes).isNull()
        }
    }

    @Test
    fun createFromPlayerWithNotes() {
        val countryCode = "US"
        val flagResProvider = FakeFlagResProvider()
        val mockFlagRes = 123
        flagResProvider.mockFlagResForCountry(countryCode, mockFlagRes)

        val player = Player(
            countryCode = countryCode,
            gamerTag = "Testy",
            realName = "McTestFace",
            notes = "Coach",
        )

        val displayModel = player.toDisplayModel(flagResProvider)

        with(displayModel) {
            assertThat(flagImage).isEqualTo(UIImage.AndroidResource(mockFlagRes))
            assertThat(gamerTag).isEqualTo("Testy")
            assertThat(realName).isEqualTo("(McTestFace)")
            assertThat(notes).isEqualTo("(Coach)")
        }
    }
}
