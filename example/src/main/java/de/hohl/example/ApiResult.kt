package de.hohl.example

sealed class ApiResult<out Success> {
    data class Success(val success: Success) : ApiResult<Success>()
    data class Failure(val failure: Exception) : ApiResult<Nothing>()
}
