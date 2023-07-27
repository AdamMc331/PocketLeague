package com.adammcneilly.pocketleague.core.feature

/**
 * Implementation of the [CommonParcelize] annotation for the JVM platform.
 *
 * In this case, we do nothing. Unlike Android, which
 * uses parcelize plugin.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
actual annotation class CommonParcelize actual constructor()
