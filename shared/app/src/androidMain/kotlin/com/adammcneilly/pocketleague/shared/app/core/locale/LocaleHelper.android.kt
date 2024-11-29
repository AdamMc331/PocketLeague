package com.adammcneilly.pocketleague.shared.app.core.locale

import java.util.Locale

/**
 * See commonMain for docs.
 */
actual fun localeHelper(): LocaleHelper {
    return AndroidLocaleHelper()
}

/**
 * An implementation of [LocaleHelper] for the Android target.
 */
private class AndroidLocaleHelper : LocaleHelper {
    override fun getCountryDisplayName(
        countryCode: String,
    ): String {
        return Locale("", countryCode).getDisplayName(Locale.getDefault())
    }
}
