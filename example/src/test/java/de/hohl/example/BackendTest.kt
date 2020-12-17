package de.hohl.example

import assertk.assertThat
import assertk.assertions.isInstanceOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.time.measureDurationForResult

class BackendTest : BaseTest() {

    @Test
    fun sendPing() = runBlocking<Unit> {
        val backendApi = BackendApiTarget().target
        val ping = backendApi.ping()
        val asApiResult = ping.asApiResult()
        assertThat(asApiResult).isInstanceOf(ApiResult.Success::class.java)
    }

    @Test
    fun coinsList() = runBlocking<Unit> {
        val backendApi = BackendApiTarget().target
        val ping = backendApi.coinsList()
        val result = ping.asApiResult()
        assertThat(result).isInstanceOf(ApiResult.Success::class.java)
        result as ApiResult.Success
    }

}

