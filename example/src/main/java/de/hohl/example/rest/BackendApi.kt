package de.hohl.example.rest

import de.hohl.backend.response.KrakenResult
import de.hohl.backend.response.ServerTime
import de.hohl.backend.response.Ticker
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val json = "Accept: application/json"

interface BackendApi {

    @Headers(json)
    @GET("public/SystemStatus")
    suspend fun ping(): Response<KrakenResult<ServerTime>>

    @Deprecated(level = DeprecationLevel.WARNING, message = "remove")
    @Headers(json)
    @GET("coins/list")
    suspend fun coinsList(): Response<List<de.hohl.backend.response.Coin>>

    @Headers(json)
    @GET("public/Time")
    suspend fun serverTime(): Response<KrakenResult<ServerTime>>

    @Headers(json)
    @GET("public/Ticker")
    suspend fun ticker(@Query("pair") pair: String): Response<KrakenResult<Ticker>>

}