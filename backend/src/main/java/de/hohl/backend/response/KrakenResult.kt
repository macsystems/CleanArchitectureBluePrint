package de.hohl.backend.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KrakenResult<T : Any>(
    @Json(name = "error") val error: List<Any>,
    @Json(name = "result") val result: T = NoResult as T
) {
    fun hasError(): Boolean {
        return error.isNotEmpty()
    }

    fun hasResult(): Boolean {
        return result !is NoResult
    }
}

object NoResult : Any()