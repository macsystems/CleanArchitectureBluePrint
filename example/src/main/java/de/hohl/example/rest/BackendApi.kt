package de.hohl.example.rest

import de.hohl.example.ApiResult
import de.hohl.example.response.Coins
import de.hohl.example.response.MarketChart
import de.hohl.example.response.PingResponse
import retrofit2.http.GET
import retrofit2.http.Query

//https://www.coingecko.com/en/api#explore-api
interface BackendApi {
    @GET("/ping")
    fun ping(): ApiResult<PingResponse>

    // @GET("/simple/price")
    // fun price(): ApiResult<PriceResult>

    @GET("/coins/list")
    fun coinsList(): ApiResult<Coins>


    @GET("/coins/{id}/market_chart")
    fun marketChart(@Query("id") id: String): ApiResult<MarketChart>

}


