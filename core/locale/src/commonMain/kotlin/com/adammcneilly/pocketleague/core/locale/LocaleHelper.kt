package com.adammcneilly.pocketleague.core.locale

/**
 * Platform specific methods related to locales, such as country/device information.
 */
interface LocaleHelper {

    /**
     * Given a [countryCode], convert it to a relevant display name. For example,
     * "us" should return "United States".
     */
    fun getCountryDisplayName(countryCode: String): String

    /**
     * Given a [countryCode], convert that information to the unicode value
     * of that country's flag emoji.
     */
    fun getFlagEmoji(countryCode: String): String

    companion object {
        const val UNICODE_CHAR_OFFSET_START = 127397
    }
}

/**
 * Provide an instance of a [LocaleHelper] for a corresponding target.
 */
expect fun provideLocaleHelper(): LocaleHelper
