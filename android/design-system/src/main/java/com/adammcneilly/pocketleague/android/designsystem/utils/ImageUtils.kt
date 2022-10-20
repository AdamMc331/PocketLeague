package com.adammcneilly.pocketleague.android.designsystem.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL

/**
 * A helper function to pull the correct image url from the supplied [ThemedImageURL]
 * based on our device theme.
 */
@Composable
fun ThemedImageURL.getForTheme(): String? {
    return if (isSystemInDarkTheme()) {
        this.darkThemeImageURL
    } else {
        this.lightThemeImageUrl
    }
}
