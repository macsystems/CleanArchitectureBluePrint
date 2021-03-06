package de.hohl.example.rest

import de.hohl.backend.response.KrakenResult
import de.hohl.backend.response.ServerTime
import de.hohl.backend.response.SystemStatus
import de.hohl.backend.response.Ticker
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val HEADER_JSON = "Accept: application/json"

interface BackendApi {

    @Headers(HEADER_JSON)
    @GET("public/SystemStatus")
    suspend fun systemStatus(): Response<KrakenResult<SystemStatus>>

    @Headers(HEADER_JSON)
    @GET("public/Time")
    suspend fun ping(): Response<KrakenResult<ServerTime>>

    @Deprecated(level = DeprecationLevel.WARNING, message = "remove")
    @Headers(HEADER_JSON)
    @GET("coins/list")
    suspend fun coinsList(): Response<List<de.hohl.backend.response.Coin>>

    @Headers(HEADER_JSON)
    @GET("public/Time")
    suspend fun serverTime(): Response<KrakenResult<ServerTime>>

    @Headers(HEADER_JSON)
    @GET("public/Ticker")
    suspend fun ticker(@Query("pair") pair: String): Response<KrakenResult<Ticker>>

}