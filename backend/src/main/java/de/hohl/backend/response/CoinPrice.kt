package de.hohl.backend.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinPrices(
    val currencies: Map<String, CoinPrice>
)

@JsonClass(generateAdapter = true)
data class CoinPrice(
    //val eur: Double,
    @Json(name = "eur_24h_change") val eur_24h_change: Double,
    @Json(name = "eur_24h_vol") val eur_24h_vol: Double,
    @Json(name = "eur_market_cap") val eur_market_cap: Double,
    @Json(name = "last_updated_at") val last_updated_at: Int
)
