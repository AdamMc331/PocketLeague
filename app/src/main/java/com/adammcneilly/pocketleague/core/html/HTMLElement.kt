package com.adammcneilly.pocketleague.core.html

/**
 * An [HTMLElement] is a specific node within the tree of HTML content.
 */
interface HTMLElement {
    /**
     * Returns the text value of this element.
     */
    fun getText(): String

    /**
     * Generates a collection of [HTMLElement] entities that match the given [elementType] and have
     * a class value of [elementClass].
     */
    fun selectAll(
        elementType: String,
        elementClass: String,
    ): List<HTMLElement>

    /**
     * Selects the first [HTMLElement] entity that matches the given [elementType] and have
     * a class value of [elementClass].
     */
    fun selectFirst(
        elementType: String,
        elementClass: String,
    ): HTMLElement?

    /**
     * Finds an attribute with the given [attributeKey] and returns its value.
     */
    fun getAttribute(
        attributeKey: String,
    ): String
}
