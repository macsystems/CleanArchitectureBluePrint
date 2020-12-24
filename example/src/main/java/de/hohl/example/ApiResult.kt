package de.hohl.example

import retrofit2.Response

typealias HttpCode = Int

sealed class ApiResult<out T : Any> {
    /**
     * Holds the result/payload of a request
     */
    data class Success<out T : Any>(val payload: T) : ApiResult<T>()

    /**
     * Regular error which can occur when doing IO
     */
    data class Failure(val httpCode: HttpCode, val message: String, val errorBody: String) :
        ApiResult<Nothing>()

    /**
     * Fatal Error, a non recoverable error. Out Of memory would be such a case
     */
    data class FatalError(val failure: Throwable) : ApiResult<Nothing>()
}


/**
 * Converts any Response into either Success or Failure.
 */
inline fun <reified T : Any> Response<T>.asApiResult(): ApiResult<T> {
    return if (isSuccessful) {
        ApiResult.Success(body() as T)
    } else {
        ApiResult.Failure(
            httpCode = code(),
            message = message(),
            errorBody = errorBody()?.run { this.string() } ?: ""
        )
    }
}

/**
 * Converts any Exception into an FatalError
 * TODO: Limiting the scope would be nice in some way
 */
fun Throwable.asApiResult(): ApiResult.FatalError {
    return ApiResult.FatalError(this)
}




