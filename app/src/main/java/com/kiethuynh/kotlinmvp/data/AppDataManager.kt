package com.kiethuynh.kotlinmvp.data

import com.kiethuynh.kotlinmvp.data.datasource.DataSource
import com.kiethuynh.kotlinmvp.data.preferences.PreferencesHelper
import javax.inject.Inject

/**
 * Created by khanhnguyen on 13/09/2017
 */
open class AppDataManager
@Inject constructor(private val preferencesHelper: PreferencesHelper, private val dataSource: DataSource) : DataManager {

    override fun getDataSource() = dataSource

    override fun getPreferencesHelper() = preferencesHelper
}