package com.adammcneilly.pocketleague.core.html

import org.jsoup.nodes.Element

class JSoupHTMLElement(
    private val element: Element,
) : HTMLElement {
    override fun getText(): String {
        return element.text()
    }

    override fun selectAll(elementType: String, elementClass: String): List<HTMLElement> {
        val elements = element.select(
            "$elementType[class*=$elementClass]"
        )

        return elements.map { element ->
            JSoupHTMLElement(element)
        }
    }

    override fun getAttribute(attributeKey: String): String {
        return element
            .attributes()
            .get(attributeKey)
    }
}
