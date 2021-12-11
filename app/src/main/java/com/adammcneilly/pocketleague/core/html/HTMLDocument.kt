package com.adammcneilly.pocketleague.core.html

/**
 * A wrapper interface that represents a document of HTML code for parsing.
 */
interface HTMLDocument {
    /**
     * Generates a collection of [HTMLElement] entities that match the given [elementType] and have
     * a class value of [elementClass].
     */
    fun selectAll(
        elementType: String,
        elementClass: String,
    ): List<HTMLElement>
}
