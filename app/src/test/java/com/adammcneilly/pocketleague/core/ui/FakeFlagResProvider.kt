package com.adammcneilly.pocketleague.core.ui

class FakeFlagResProvider : FlagResProvider {
    private val flagResResponses: MutableMap<String, Int> = mutableMapOf()

    fun mockFlagResForCountry(
        countryCode: String,
        flagRes: Int,
    ) {
        flagResResponses[countryCode] = flagRes
    }

    override fun getFlagRes(countryCode: String): Int {
        return flagResResponses[countryCode]!!
    }
}
