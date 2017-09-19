package com.kiethuynh.kotlinmvp.data.datasource

import ch.smartlink.framework.data.retrofit.API
import com.kiethuynh.kotlinmvp.data.preferences.PreferencesHelper
import com.kiethuynh.kotlinmvp.data.retrofit.response.User
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by khanhnguyen on 13/09/2017
 */
open class RemoteDataSource @Inject constructor(private val api: API, private val preferencesHelper: PreferencesHelper) : DataSource {
    override fun login(user: User): Single<String> {
        return api.login(user).flatMap { response ->
            if (response.isSuccessful) Single.just(response.headers().get("Authorization"))
            else Single.error(Exception())
        }
    }
}