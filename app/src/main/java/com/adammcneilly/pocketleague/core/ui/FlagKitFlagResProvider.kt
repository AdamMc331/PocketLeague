package com.adammcneilly.pocketleague.core.ui

import android.content.Context
import com.murgupluoglu.flagkit.FlagKit

/**
 * A concrete implementation of a [FlagResProvider] that uses the [FlagKit] API.
 */
class FlagKitFlagResProvider(
    private val context: Context,
) : FlagResProvider {

    override fun getFlagRes(countryCode: String): Int {
        return FlagKit.getResId(context, countryCode)
    }
}
