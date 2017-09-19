package ch.smartlink.framework.data.retrofit

import com.kiethuynh.kotlinmvp.common.EndPoints
import com.kiethuynh.kotlinmvp.data.retrofit.response.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by khanhnguyen on 13/09/2017
 */
interface API {
    @POST(EndPoints.LOGIN)
    fun login(@Body user: User) : Single<Response<Any>>
}