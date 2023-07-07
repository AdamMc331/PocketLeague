package com.adammcneilly.pocketleague.core.locale

import java.util.Locale

/**
 * Given a [countryCode], convert it to a relevant display name. For example,
 * "us" should return "United States".
 */
actual fun getCountryDisplayName(countryCode: String): String {
    return Locale("", countryCode).getDisplayName(Locale.getDefault())
}
