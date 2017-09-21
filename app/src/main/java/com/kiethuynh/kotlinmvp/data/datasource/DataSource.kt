package com.kiethuynh.kotlinmvp.data.datasource

import com.kiethuynh.kotlinmvp.data.retrofit.response.User
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by khanhnguyen on 13/09/2017
 */
interface DataSource {
    fun login(user: User): Observable<String>
}