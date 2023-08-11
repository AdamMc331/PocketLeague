package com.adammcneilly.pocketleague.core.locale

import java.util.Locale

/**
 * An implementation of [LocaleHelper] for the JVM target.
 */
class JVMLocaleHelper : LocaleHelper {

    override fun getCountryDisplayName(countryCode: String): String {
        return Locale("", countryCode).getDisplayName(Locale.getDefault())
    }

    override fun getFlagEmoji(countryCode: String): String {
        val codePoints = countryCode
            .uppercase()
            .map(Char::toCodePoint)
            .toIntArray()

        return String(codePoints, 0, 2)
    }
}

/**
 * See commonMain for docs.
 */
actual fun provideLocaleHelper(): LocaleHelper {
    return JVMLocaleHelper()
}
