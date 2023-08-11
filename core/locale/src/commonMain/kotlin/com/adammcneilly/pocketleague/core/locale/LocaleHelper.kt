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
}

/**
 * To understand where this number came from, read this post: https://dev.to/jorik/country-code-to-flag-emoji-a21
 *
 * TL;DR -> Unicode codepoint for 'A' - UTF-16 value for 'A'.
 * We can add UTF-16 value for any char to this constant, and get the unicode
 * codepoint for that char.
 */
fun Char.toCodePoint(): Int {
    val unicodeOffsetStart = 127397

    return this.code + unicodeOffsetStart
}

/**
 * Provide an instance of a [LocaleHelper] for a corresponding target.
 */
expect fun provideLocaleHelper(): LocaleHelper
