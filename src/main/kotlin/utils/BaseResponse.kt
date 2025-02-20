package com.example.utils

import io.ktor.http.*

sealed class BaseResponse<T>(
    //val statusCode: HttpStatusCode = HttpStatusCode.OK
) {
    data class SuccessResponse<T>(
        val status: Boolean,
        val message: String? = null,
        val data: T? = null,
    ) : BaseResponse<T>()

    data class ErrorResponse<T>(
        val status: Boolean,
        val message: String? = null,
        val data: T? = null,
    ) : BaseResponse<T>()
}