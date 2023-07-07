package com.adammcneilly.pocketleague.core.locale

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.localizedStringForCountryCode

/**
 * iOS specific implementation of a [LocaleHelper].
 */
class IOSLocaleHelper : LocaleHelper {
    override fun getCountryDisplayName(countryCode: String): String {
        return NSLocale.currentLocale().localizedStringForCountryCode(countryCode).orEmpty()
    }
}

/**
 * See commonMain for docs.
 */
actual fun provideLocaleHelper(): LocaleHelper {
    return IOSLocaleHelper()
}
