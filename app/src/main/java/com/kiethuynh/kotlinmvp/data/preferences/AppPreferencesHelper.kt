package com.kiethuynh.kotlinmvp.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by khanhnguyen on 13/09/2017
 */
open class AppPreferencesHelper @Inject constructor(private val sharedPreferences: SharedPreferences) : PreferencesHelper {

    val PREFS_ACCESS_TOKEN = "ACCESS_TOKEN"

    override fun setAccessToken(accessToken: String?) {
        sharedPreferences.edit().putString(PREFS_ACCESS_TOKEN, accessToken).apply()
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(PREFS_ACCESS_TOKEN, null)
    }

}