package de.hohl.example

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response
import java.io.IOException


class ApiResultTest {
    @Test
    fun `check we transform response into success`() {
        val expected = "some value"
        val given = "some value"
        val response = Response.success(given)
        val result = response.asApiResult()
        assertThat(result).isInstanceOf(ApiResult.Success::class.java)
        result as ApiResult.Success
        assertThat(result.payload).isEqualTo(expected)
    }

    @Test
    fun `check we transform response into failure`() {
        val expectedBody = "some value"
        val given = "some value"
        val http404 = 404
        val expectedHttpCode = 404
        val response = Response.error<String>(http404, given.toResponseBody())

        val result = response.asApiResult()
        assertThat(result).isInstanceOf(ApiResult.Failure::class.java)
        result as ApiResult.Failure
        assertThat(result.httpCode).isEqualTo(expectedHttpCode)
        assertThat(result.errorBody).isEqualTo(expectedBody)
    }

    @Test
    fun `check for fatal error`() {
        val exception = IOException("some problem")
        val result = exception.asApiResult()

        assertThat(result).isInstanceOf(ApiResult.FatalError::class.java)
        assertThat(result.failure).isInstanceOf(IOException::class.java)
        assertThat(result.failure.message).isEqualTo("some problem")

    }

}