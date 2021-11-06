package com.adammcneilly.pocketleague.core.ui

/**
 * This is a wrapper around any service that can provide image resources for a flag based on a
 * country code.
 */
interface FlagResProvider {
    fun getFlagRes(countryCode: String): Int
}
