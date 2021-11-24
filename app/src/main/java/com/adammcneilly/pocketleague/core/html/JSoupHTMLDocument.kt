package com.adammcneilly.pocketleague.core.html

import org.jsoup.nodes.Document

class JSoupHTMLDocument(
    private val document: Document,
) : HTMLDocument {

    override fun selectAll(elementType: String, elementClass: String): List<HTMLElement> {
        val elements = document.select("$elementType[class*=$elementClass]")

        return elements.map { element ->
            JSoupHTMLElement(element)
        }
    }
}
