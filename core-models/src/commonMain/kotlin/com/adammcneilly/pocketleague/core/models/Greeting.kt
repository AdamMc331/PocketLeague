package com.adammcneilly.pocketleague.core.models

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
