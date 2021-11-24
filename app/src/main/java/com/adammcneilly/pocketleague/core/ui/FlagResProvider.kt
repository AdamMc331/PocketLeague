package com.adammcneilly.pocketleague.core.ui

/**
 * This is a wrapper around any service that can provide image resources for a flag based on a
 * country code.
 */
interface FlagResProvider {
    /**
     * Given a [countryCode], fetch the corresponding drawable resource for that country's flag.
     */
    fun getFlagRes(countryCode: String): Int
}
