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

    override fun getFlagEmoji(countryCode: String): String {
        // Not sure how to get this to work, what's below is from SO on converting in Obj-C.
        return ""
        //    wchar_t bytes[2] = {
        //        base +[countryCode characterAtIndex:0],
        //        base +[countryCode characterAtIndex:1]
        //    };
        //
        //    return [[NSString alloc] initWithBytes:bytes
        //                                    length:countryCode.length *sizeof(wchar_t)
        //                                  encoding:NSUTF32LittleEndianStringEncoding];
    }
}

/**
 * See commonMain for docs.
 */
actual fun provideLocaleHelper(): LocaleHelper {
    return IOSLocaleHelper()
}
