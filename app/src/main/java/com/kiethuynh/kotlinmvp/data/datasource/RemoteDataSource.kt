package com.kiethuynh.kotlinmvp.data.datasource

import ch.smartlink.framework.data.retrofit.API
import com.kiethuynh.kotlinmvp.common.ErrorHandler
import com.kiethuynh.kotlinmvp.data.preferences.PreferencesHelper
import com.kiethuynh.kotlinmvp.data.retrofit.response.User
import io.reactivex.Observable
import io.reactivex.Single
import java.util.logging.ErrorManager
import javax.inject.Inject

/**
 * Created by khanhnguyen on 13/09/2017
 */
open class RemoteDataSource @Inject constructor(private val api: API, private val preferencesHelper: PreferencesHelper) : DataSource {
    override fun login(user: User): Observable<String> {
        return api.login(user).flatMap { response ->
            if (response.isSuccessful) Observable.just(response.headers().get("Authorization"))
            else Observable.error(ErrorHandler.getError(response.code()))
        }
    }
}