package com.adammcneilly.pocketleague.core.ui


/**
 * This sealed class defines all of the different formats an image can be represented in UI code.
 */
sealed class UIImage {

    /**
     * This class is used when we want to show an image stored in a specific [drawableRes].
     */
    data class AndroidResource(val drawableRes: Int) : UIImage()

    /**
     * This class is used when we have an image at a remote [imageUrl] that must be rendered.
     */
    data class Remote(val imageUrl: String) : UIImage()
}
