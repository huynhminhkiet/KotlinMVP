package com.kiethuynh.kotlinmvp.common

import com.kiethuynh.kotlinmvp.exceptions.InvalidTokenException
import java.net.UnknownHostException

/**
 * Created by kiethuynh on 21/09/2017
 */
class ErrorHandler {
    companion object {
        fun getError(code: Int): Exception {
            when (code) {
                401 -> return InvalidTokenException()
                else -> return Exception()
            }
        }

        fun handleError(throwable: Throwable, errorCallback: ErrorCallback) {
            when(throwable) {
                is InvalidTokenException -> errorCallback.onTokenInvalidError()
                is UnknownHostException -> errorCallback.onNoInternetConnection()
                else -> errorCallback.onOtherError()
            }
        }
    }
}