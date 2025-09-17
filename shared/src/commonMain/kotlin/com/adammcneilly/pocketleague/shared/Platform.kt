package com.adammcneilly.pocketleague.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
