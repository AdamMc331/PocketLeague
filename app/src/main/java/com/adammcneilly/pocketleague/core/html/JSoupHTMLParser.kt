package com.adammcneilly.pocketleague.core.html

import org.jsoup.Jsoup
import javax.inject.Inject

class JSoupHTMLParser @Inject constructor() : HTMLParser {
    private lateinit var document: HTMLDocument

    override fun setHTML(html: String) {
        val doc = Jsoup.parse(html)
        document = JSoupHTMLDocument(doc)
    }

    override fun selectAll(elementType: String, elementClass: String): List<HTMLElement> {
        return document.selectAll(elementType, elementClass)
    }
}
