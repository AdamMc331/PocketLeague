package com.adammcneilly.pocketleague.shared.app.core.locale

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.localizedStringForCountryCode

/**
 * See commonMain for docs.
 */
actual fun localeHelper(): LocaleHelper {
    return IOSLocaleHelper()
}

/**
 * iOS specific implementation of a [LocaleHelper].
 */
private class IOSLocaleHelper : LocaleHelper {
    override fun getCountryDisplayName(
        countryCode: String,
    ): String {
        return NSLocale
            .currentLocale()
            .localizedStringForCountryCode(countryCode)
            .orEmpty()
    }
}
