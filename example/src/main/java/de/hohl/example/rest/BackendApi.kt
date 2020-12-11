package de.hohl.example.rest

import de.hohl.example.response.Coins
import de.hohl.example.response.PingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface BackendApi {
    @Headers("Accept: application/json")
    @GET("ping")
    suspend fun ping(): Response<PingResponse>

    // @GET("/simple/price")
    // fun price(): ApiResult<PriceResult>

    @Headers("Accept: application/json")
    @GET("coins/list")
    suspend fun coinsList(): Response<Coins>
/*
    @Headers("Accept: application/json")
    @GET("/coins/{id}/market_chart")
    suspend fun marketChart(@Query("id") id: String): ApiResult<MarketChart>
*/
}