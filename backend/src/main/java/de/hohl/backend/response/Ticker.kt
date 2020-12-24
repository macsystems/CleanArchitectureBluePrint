package de.hohl.backend.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ticker(val a: String)