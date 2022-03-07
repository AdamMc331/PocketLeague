package com.adammcneilly.pocketleague.shared

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias URI = String

/**
 * A unique identifier for a screen.
 *
 * @property[screen] The actual screen we want to identify.
 * @property[params] Any parameters that should be passed into this screen when it's created.
 * @property[paramsAsString] The [params], but string encoded?
 */
class ScreenIdentifier private constructor(
    val screen: Screens,
    var params: ScreenParams? = null,
    var paramsAsString: String? = null,
) {

    val uri: URI
        get() = returnURI()

    /**
     * Factory methods to create a [ScreenIdentifier].
     */
    companion object Factory {
        /**
         * Create a [ScreenIdentifier] for the supplied [screen] and its [params].
         */
        fun get(screen: Screens, params: ScreenParams?): ScreenIdentifier {
            return ScreenIdentifier(screen, params, null)
        }

        /**
         * Given a [uri], create a [ScreenIdentifier] if the uri matches one of the screens.
         */
        fun getByURI(uri: String): ScreenIdentifier? {
            val parts = uri.split(":")
            Screens.values().forEach {
                if (it.asString == parts[0]) {
                    return ScreenIdentifier(it, null, parts[1])
                }
            }
            return null
        }
    }

    private fun returnURI(): String {
        if (paramsAsString != null) {
            return screen.asString + ":" + paramsAsString
        }
        val toString = params.toString() // returns `ClassParams(A=1&B=2)`
        val startIndex = toString.indexOf("(")
        val paramsString = toString.substring(startIndex + 1, toString.length - 1)
        return screen.asString + ":" + paramsString
    }

    /**
     * Unlike the [params] property, this reified function returns the specific type and not
     * the generic [ScreenParams] interface type.
     */
    inline fun <reified T : ScreenParams> params(): T {
        if (params == null && paramsAsString != null) {
            val jsonValues = paramsStrToJson(paramsAsString!!)
            params = Json.decodeFromString<T>("""{$jsonValues}""")
        }
        return params as T
    }

    /**
     * Convert the parameter string for this screen into JSON.
     */
    fun paramsStrToJson(paramsAsString: String): String {
        // converts `A=1&B=1` into `"A":"1","B":"2"`
        val elements = paramsAsString.split("&")
        var jsonValues = ""
        elements.forEach {
            if (jsonValues != "") {
                jsonValues += ","
            }
            val parts = it.split("=")
            jsonValues += "\"${parts[0]}\":\"${parts[1]}\""
        }
        return jsonValues
    }

    /**
     * Get the initialization settings for this screen.
     */
    fun getScreenInitSettings(navigation: Navigation): ScreenInitSettings {
        return screen.initSettings(navigation, this)
    }

    /**
     * Determines if a vertical backstack is supported for this screen.
     */
    fun level1VerticalBackstackEnabled(): Boolean {
        Level1Navigation.values().forEach {
            if (it.screenIdentifier.uri == this.uri && it.rememberVerticalStack) {
                return true
            }
        }
        return false
    }
}
