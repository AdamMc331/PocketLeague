package com.adammcneilly.pocketleague.shared.app

/**
 * Implementation of the [CommonParcelize] annotation for the iOS platform.
 *
 * In this case, we do nothing. Unlike Android, which
 * uses parcelize plugin.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
actual annotation class CommonParcelize actual constructor()
