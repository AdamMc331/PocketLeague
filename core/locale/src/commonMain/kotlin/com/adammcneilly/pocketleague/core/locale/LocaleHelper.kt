package com.adammcneilly.pocketleague.core.locale

import de.cketti.codepoints.CodePoints

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
    fun getFlagEmoji(countryCode: String): String {
        if (countryCode.length != 2) {
            throw IllegalArgumentException("Country code \"$countryCode\" invalid, expected two characters.")
        }

        val upperCountryCode = countryCode.uppercase()

        return overrideEmojis[upperCountryCode] ?: run {
            upperCountryCode
                .map(Char::toCodePoint)
                .map(CodePoints::toChars)
                .joinToString(separator = "") { charArray ->
                    charArray.concatToString()
                }
        }
    }

    companion object {
        /**
         * Some Rocket League plays have a country code of "EN" for England. However, the England flag emoji
         * is actually a combination of letters and other emojis.
         *
         * In this case, we'll just hardcode it.
         *
         * https://emojipedia.org/flag-england
         */
        @Suppress("MaxLineLength")
        private const val ENGLAND_FLAG_EMOJI = "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F"

        /**
         * Most emojis are generated in [getFlagEmoji] from the country code, but if there are any country codes
         * we want to override, this map will take precedence.
         */
        private val overrideEmojis = mapOf(
            "EN" to ENGLAND_FLAG_EMOJI,
        )
    }
}

/**
 * To understand where this number came from, read this post: https://dev.to/jorik/country-code-to-flag-emoji-a21
 *
 * TL;DR -> Unicode codepoint for 'A' - UTF-16 value for 'A'.
 * We can add UTF-16 value for any char to this constant, and get the unicode
 * codepoint for that char.
 */
@Suppress("MagicNumber")
fun Char.toCodePoint(): Int {
    val unicodeOffsetStart = 127397

    return this.code + unicodeOffsetStart
}

/**
 * Provide an instance of a [LocaleHelper] for a corresponding target.
 */
expect fun provideLocaleHelper(): LocaleHelper
