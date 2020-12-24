package de.hohl.backend.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SystemStatus(
    @Json(name = "status") val status: Status,
    @Json(name = "timestamp") val timestamp: String
)