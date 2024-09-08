package org.codewithak.dailypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform