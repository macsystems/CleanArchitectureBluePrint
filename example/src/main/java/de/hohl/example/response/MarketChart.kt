package de.hohl.example.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketChart(val abc: Int)
