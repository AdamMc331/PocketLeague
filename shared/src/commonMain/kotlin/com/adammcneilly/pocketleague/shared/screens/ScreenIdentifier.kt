package com.adammcneilly.pocketleague.shared.screens

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * This defines a unique identifier for a [screen] within the app. This is different from [Screen]
 * because the underlying UI might be identical, but the [params] could be different and as such
 * should be treated as a new identifier.
 */
class ScreenIdentifier private constructor(
    val screen: Screen,
    var params: ScreenParams? = null,
    val paramsAsString: String? = null,
) {
    /**
     * Builds a string that represents a unique URI for this [screen] and its [params].
     */
    val uri: String
        get() = returnURI()

    private fun returnURI(): String {
        if (paramsAsString != null) {
            return screen.asString + ":" + paramsAsString
        }

        // Returns ClassParams(A=1&B=2)
        val toString = params.toString()

        val startIndex = toString.indexOf("(")

        val paramsString = toString.substring(startIndex + 1, toString.length - 1)

        return screen.asString + ":" + paramsString
    }

    /**
     * Unlike the [params] property, this reified function returns the specific type of params and
     * not the generic [ScreenParams] interface type.
     */
    inline fun <reified T : ScreenParams> params(): T {
        if (params == null && paramsAsString != null) {
            val jsonValues = paramsStrToJson(paramsAsString)
            params = Json.decodeFromString<T>(jsonValues)
        }

        return params as T
    }

    /**
     * Converts the [paramsAsString] into a JSON string.
     */
    fun paramsStrToJson(paramsAsString: String): String {
        // Converts `A=1&B=2` into `"A":"1","B":"2"`
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
     * Given some [navigation], get the [ScreenInitSettings] for this identifier.
     */
    fun getScreenInitSettings(navigation: Navigation): ScreenInitSettings {
        return screen.initSettings(navigation, this)
    }

    /**
     * Determines if, for this screen, we support a vertical backstack.
     */
    fun level1VerticalBackstackEnabled(): Boolean {
        Level1Navigation.values().forEach {
            if (it.screenIdentifier.uri == this.uri && it.rememberVerticalStack) {
                return true
            }
        }

        return false
    }

    companion object Factory {
        fun get(screen: Screen, params: ScreenParams?): ScreenIdentifier {
            return ScreenIdentifier(screen, params, null)
        }

        fun getByURI(uri: String): ScreenIdentifier? {
            val parts = uri.split(":")

            Screen.values().forEach {
                if (it.asString == parts[0]) {
                    return ScreenIdentifier(it, null, parts[1])
                }
            }

            return null
        }
    }
}
