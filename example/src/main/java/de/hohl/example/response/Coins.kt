package de.hohl.example.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coins(val coins: List<Coin>)

@JsonClass(generateAdapter = true)
data class Coin(val id: String, val symbol: String, val name: String)
