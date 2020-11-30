package de.hohl.example.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//"gecko_says": "(V3) To the Moon!"

@JsonClass(generateAdapter = true)
data class PingResponse(@Json(name = "gecko_says") val gecko_says: String)