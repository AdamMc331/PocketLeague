package com.adammcneilly.pocketleague.core.html

import org.jsoup.nodes.Document

/**
 * A concrete implementation of an [HTMLDocument] that is backed by a jsoup [document].
 */
class JSoupHTMLDocument(
    private val document: Document,
) : HTMLDocument {

    override fun selectAll(elementType: String, elementClass: String): List<HTMLElement> {
        val selector = StringBuilder(elementType).apply {
            if (elementClass.isNotEmpty()) {
                append(".$elementClass")
            }
        }.toString()

        val elements = document.select(selector)

        return elements.map { element ->
            JSoupHTMLElement(element)
        }
    }
}