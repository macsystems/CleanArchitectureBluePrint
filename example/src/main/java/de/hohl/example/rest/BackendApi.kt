package de.hohl.example.rest

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface BackendApi {
    @Headers("Accept: application/json")
    @GET("ping")
    suspend fun ping(): Response<de.hohl.backend.response.PingResponse>

    // @GET("/simple/price")
    // fun price(): ApiResult<PriceResult>

    @Headers("Accept: application/json")
    @GET("coins/list")
    suspend fun coinsList(): Response<List<de.hohl.backend.response.Coin>>
/*
    @Headers("Accept: application/json")
    @GET("/coins/{id}/market_chart")
    suspend fun marketChart(@Query("id") id: String): ApiResult<MarketChart>
*/
}