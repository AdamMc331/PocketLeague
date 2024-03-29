package com.adammcneilly.pocketleague.core.feature

/**
 * Common annotation class used so we can use the
 * Parcelize annotation on Android.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class CommonParcelize()
