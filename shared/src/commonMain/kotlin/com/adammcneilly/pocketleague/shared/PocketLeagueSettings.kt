package com.adammcneilly.pocketleague.shared

import com.russhwolf.settings.Settings
import com.russhwolf.settings.long
import com.russhwolf.settings.string

/**
 * A class to manage the user's local settings.
 *
 * Will read and write values from the supplied [settings].
 */
class PocketLeagueSettings(settings: Settings) {

    var listCacheTimestamp by settings.long(defaultValue = 0)
    var savedLevel1URI by settings.string(defaultValue = Level1Navigation.EventSummaries.screenIdentifier.uri)
}
