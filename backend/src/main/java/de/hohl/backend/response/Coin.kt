package de.hohl.backend.response

@com.squareup.moshi.JsonClass(generateAdapter = true)
data class Coin(
    @com.squareup.moshi.Json(name = "id") val id: String,
    @com.squareup.moshi.Json(name = "symbol") val symbol: String,
    @com.squareup.moshi.Json(name = "name") val name: String
)