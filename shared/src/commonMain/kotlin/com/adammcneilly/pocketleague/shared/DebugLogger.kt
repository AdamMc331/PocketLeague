package com.adammcneilly.pocketleague.shared

expect class DebugLogger(tagString: String) {
    val tag: String
    fun log(message: String)
}
