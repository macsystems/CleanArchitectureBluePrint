package de.hohl.backend.response

@com.squareup.moshi.JsonClass(generateAdapter = true)
data class Coin(val id: String, val symbol: String, val name: String)