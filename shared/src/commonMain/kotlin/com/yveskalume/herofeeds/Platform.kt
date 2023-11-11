package com.yveskalume.herofeeds

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform