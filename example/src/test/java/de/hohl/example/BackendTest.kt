package de.hohl.example

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isGreaterThan
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotEmpty
import de.hohl.backend.response.KrakenResult
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BackendTest : BaseTest() {

    @Test
    fun serverTime() = runBlocking<Unit> {
        with(BackendApiTarget().target) {
            val serverTime = serverTime()
            val result = serverTime.asApiResult()
            assertThat(result).isInstanceOf(ApiResult.Success::class.java)
            val success = result as ApiResult.Success
            assertThat(success.payload).isInstanceOf(KrakenResult::class.java)
            assertThat(success.payload.error.isEmpty()).isEqualTo(true)
            with(success.payload.result) {
                assertThat(rfc1123).isNotEmpty()
                assertThat(unixtime).isGreaterThan(0)
            }
        }
    }

    @Test
    fun ticker() = runBlocking<Unit> {
        with(BackendApiTarget().target) {
            val result = ticker("test").asApiResult()
            assertThat(result).isInstanceOf(ApiResult.Success::class.java)
        }
    }


    @Test
    fun sendPing() = runBlocking<Unit> {
        val backendApi = BackendApiTarget().target
        val ping = backendApi.ping()
        val asApiResult = ping.asApiResult()
        assertThat(asApiResult).isInstanceOf(ApiResult.Success::class.java)
    }

}

