package com.adammcneilly.pocketleague.models

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
