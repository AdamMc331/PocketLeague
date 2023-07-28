package com.adammcneilly.pocketleague.core.locale

import java.util.Locale

/**
 * An implementation of [LocaleHelper] for the Android target.
 */
class AndroidLocaleHelper : LocaleHelper {

    override fun getCountryDisplayName(countryCode: String): String {
        return Locale("", countryCode).getDisplayName(Locale.getDefault())
    }
}

/**
 * See commonMain for docs.
 */
actual fun provideLocaleHelper(): LocaleHelper {
    return AndroidLocaleHelper()
}
