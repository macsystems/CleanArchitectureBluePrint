package de.hohl.backend.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerTime(
    @Json(name = "rfc1123") val rfc1123: String,
    @Json(name = "unixtime") val unixTime: Long
)