package com.adammcneilly.pocketleague.core.ui

import androidx.annotation.DrawableRes

sealed class UIImage {
    data class Resource(@DrawableRes val drawableRes: Int) : UIImage()

    data class Remote(val imageUrl: String) : UIImage()
}
