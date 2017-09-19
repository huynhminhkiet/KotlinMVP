package com.kiethuynh.kotlinmvp.data.preferences

/**
 * Created by khanhnguyen on 13/09/2017
 */
interface PreferencesHelper {
    fun setAccessToken(accessToken: String?)
    fun getAccessToken(): String?
}