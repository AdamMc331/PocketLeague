package com.adammcneilly.pocketleague.core.html

import org.jsoup.nodes.Element

class JSoupHTMLElement(
    private val element: Element,
) : HTMLElement {
    override fun getText(): String {
        return element.text()
    }

    override fun selectAll(elementType: String, elementClass: String): List<HTMLElement> {
        val selector = StringBuilder(elementType).apply {
            if (elementClass.isNotEmpty()) {
                append(".$elementClass")
            }
        }.toString()

        val elements = element.select(selector)

        return elements.map { element ->
            JSoupHTMLElement(element)
        }
    }

    override fun selectFirst(elementType: String, elementClass: String): HTMLElement? {
        return selectAll(elementType, elementClass).firstOrNull()
    }

    override fun getAttribute(attributeKey: String): String {
        return element
            .attributes()
            .get(attributeKey)
    }
}
