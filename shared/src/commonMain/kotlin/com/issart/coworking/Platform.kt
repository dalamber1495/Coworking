package com.issart.coworking

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform