package com.kiethuynh.kotlinmvp.common

/**
 * Created by kiethuynh on 21/09/2017
 */
interface ErrorCallback {
    fun onTokenInvalidError()
    fun onUnauthorizedError()
    fun onForbiddenError()
    fun onNoInternetConnection()
    fun onOtherError()
}