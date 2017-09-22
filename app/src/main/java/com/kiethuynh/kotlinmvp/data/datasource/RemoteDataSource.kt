package com.kiethuynh.kotlinmvp.data.datasource

import ch.smartlink.framework.data.retrofit.API
import com.kiethuynh.kotlinmvp.common.ErrorHandler
import com.kiethuynh.kotlinmvp.data.model.User
import com.kiethuynh.kotlinmvp.data.preferences.PreferencesHelper
import com.kiethuynh.kotlinmvp.data.retrofit.request.UserRequest
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by khanhnguyen on 13/09/2017
 */
open class RemoteDataSource @Inject constructor(private val api: API, private val preferencesHelper: PreferencesHelper) : DataSource {
    override fun login(userRequest: UserRequest): Observable<String> {
        return api.login(userRequest).flatMap { response ->
            if (response.isSuccessful) Observable.just(response.headers().get("Authorization"))
            else Observable.error(ErrorHandler.getError(response.code()))
        }
    }

    override fun getProfile(): Observable<User> {
        return api.getProfile(preferencesHelper.getAccessToken()).flatMap { response ->
            if (response.isSuccessful) Observable.just(response.body())
            else Observable.error(ErrorHandler.getError(response.code()))
        }
    }
}