package com.kiethuynh.kotlinmvp.common

import com.kiethuynh.kotlinmvp.exceptions.ForbiddenException
import com.kiethuynh.kotlinmvp.exceptions.InvalidTokenException
import com.kiethuynh.kotlinmvp.exceptions.UnauthorizedException
import java.net.UnknownHostException

/**
 * Created by kiethuynh on 21/09/2017
 */
class ErrorHandler {
    companion object {
        fun getError(code: Int): Exception {
            return when (code) {
                401 -> UnauthorizedException()
                403 -> ForbiddenException()
                500 -> InvalidTokenException()
                else -> Exception()
            }
        }

        fun handleError(throwable: Throwable, errorCallback: ErrorCallback) {
            when (throwable) {
                is InvalidTokenException -> errorCallback.onTokenInvalidError()
                is UnauthorizedException -> errorCallback.onUnauthorizedError()
                is ForbiddenException -> errorCallback.onForbiddenError()
                is UnknownHostException -> errorCallback.onNoInternetConnection()
                else -> errorCallback.onOtherError()
            }
        }

        fun handleError(throwable: Throwable, errorCallback: ErrorCallback, runnable: Runnable) {
            when (throwable) {
                is InvalidTokenException -> {
                    runnable.run()
                }
                is UnauthorizedException -> errorCallback.onUnauthorizedError()
                is ForbiddenException -> errorCallback.onForbiddenError()
                is UnknownHostException -> errorCallback.onNoInternetConnection()
                else -> errorCallback.onOtherError()
            }
        }
    }
}