package br.com.alexfelipe.testappkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform