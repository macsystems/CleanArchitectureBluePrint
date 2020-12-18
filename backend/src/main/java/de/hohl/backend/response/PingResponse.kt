package de.hohl.backend.response

@com.squareup.moshi.JsonClass(generateAdapter = true)
data class PingResponse(@com.squareup.moshi.Json(name = "gecko_says") val gecko_says: String)