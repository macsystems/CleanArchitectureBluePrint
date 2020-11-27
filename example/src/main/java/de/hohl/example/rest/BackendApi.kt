package de.hohl.example.rest

import retrofit2.http.GET

//https://www.coingecko.com/en/api#explore-api
interface BackendApi {
    @GET
    fun ping(): Unit
}