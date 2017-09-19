package com.kiethuynh.kotlinmvp.data

import com.kiethuynh.kotlinmvp.data.datasource.DataSource
import com.kiethuynh.kotlinmvp.data.preferences.PreferencesHelper

/**
 * Created by khanhnguyen on 13/09/2017
 */
interface DataManager {
    fun getDataSource(): DataSource
    fun getPreferencesHelper(): PreferencesHelper
}