package com.adammcneilly.pocketleague.ui.sizeconfigs

/**
 * Defines how content will be rendered within available space in the application.
 *
 * @property[SINGLE_PANE] If selected, any screens in the app are the only screens visible to
 * the user.
 * @property[DUAL_PANE] If selected, users will see two screens visible at a time.
 */
enum class ContentType {
    SINGLE_PANE,
    DUAL_PANE,
}
