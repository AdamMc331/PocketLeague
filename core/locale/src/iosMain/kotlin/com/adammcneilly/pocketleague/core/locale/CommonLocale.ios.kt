package com.adammcneilly.pocketleague.core.locale

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.localizedStringForCountryCode

/**
 * See commonMain for docs.
 */
actual fun getCountryDisplayName(countryCode: String): String {
    return NSLocale.currentLocale().localizedStringForCountryCode(countryCode).orEmpty()
}
