package com.adammcneilly.pocketleague.core.html

/**
 * A wrapper interface for any libraries used to parse HTML text.
 */
interface HTMLParser {
    /**
     * Sets the [html] we want to parse.
     */
    fun setHTML(html: String)

    /**
     * Generates a collection of [HTMLElement] entities that match the given [elementType] and have
     * a class value of [elementClass].
     */
    fun selectAll(
        elementType: String,
        elementClass: String,
    ): List<HTMLElement>
}
