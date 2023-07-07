package com.adammcneilly.pocketleague.core.locale

/**
 * Given a [countryCode], convert it to a relevant display name. For example,
 * "us" should return "United States".
 */
expect fun getCountryDisplayName(countryCode: String): String
