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
}

/**
 * Provide an instance of a [LocaleHelper] for a corresponding target.
 */
expect fun provideLocaleHelper(): LocaleHelper
