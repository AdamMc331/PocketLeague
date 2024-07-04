package com.adammcneilly.pocketleague.core.feature

/**
 * Custom parclize annotation to use for our Circuit UI screens. Not 100% sure why we need this,
 * stole idea from TiVi's Kotlin 2.0 update: https://github.com/chrisbanes/tivi/pull/1827/files
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class Parcelize
