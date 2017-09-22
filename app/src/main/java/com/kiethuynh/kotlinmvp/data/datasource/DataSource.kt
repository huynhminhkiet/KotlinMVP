package com.kiethuynh.kotlinmvp.data.datasource

import com.kiethuynh.kotlinmvp.data.model.User
import com.kiethuynh.kotlinmvp.data.retrofit.request.UserRequest
import io.reactivex.Observable

/**
 * Created by khanhnguyen on 13/09/2017
 */
interface DataSource {
    fun login(userRequest: UserRequest): Observable<String>
    fun getProfile(): Observable<User>
}