package com.adammcneilly.pocketleague.shared

import com.russhwolf.settings.Settings
import com.russhwolf.settings.long
import com.russhwolf.settings.string

class PocketLeagueSettings(s: Settings) {

    // here we define all our local settings properties,
    // by using the MultiplatformSettings library delegated properties

    var listCacheTimestamp by s.long(defaultValue = 0)
    var savedLevel1URI by s.string(defaultValue = Level1Navigation.EventSummaries.screenIdentifier.URI)
}
