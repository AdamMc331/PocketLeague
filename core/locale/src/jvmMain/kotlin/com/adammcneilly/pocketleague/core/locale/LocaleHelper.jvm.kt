package com.adammcneilly.pocketleague.core.locale

import java.util.Locale

/**
 * An implementation of [LocaleHelper] for the JVM target.
 */
class JVMLocaleHelper : LocaleHelper {
    override fun getCountryDisplayName(
        countryCode: String,
    ): String {
        return Locale("", countryCode).getDisplayName(Locale.getDefault())
    }
}

/**
 * See commonMain for docs.
 */
actual fun provideLocaleHelper(): LocaleHelper {
    return JVMLocaleHelper()
}
