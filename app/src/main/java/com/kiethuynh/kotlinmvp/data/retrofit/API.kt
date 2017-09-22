package ch.smartlink.framework.data.retrofit

import com.kiethuynh.kotlinmvp.common.EndPoints
import com.kiethuynh.kotlinmvp.data.model.User
import com.kiethuynh.kotlinmvp.data.retrofit.request.UserRequest
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by khanhnguyen on 13/09/2017
 */
interface API {
    @POST(EndPoints.LOGIN)
    fun login(@Body userRequest: UserRequest) : Observable<Response<Void>>

    @GET(EndPoints.GET_PROFILE)
    fun getProfile(@Header("Authorization") accessToken: String?) : Observable<Response<User>>
}