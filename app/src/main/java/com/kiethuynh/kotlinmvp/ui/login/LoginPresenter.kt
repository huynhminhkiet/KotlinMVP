package com.kiethuynh.kotlinmvp.ui.login

import ch.smartlink.framework.mvpbase.BasePresenter
import com.kiethuynh.kotlinmvp.common.ErrorHandler
import com.kiethuynh.kotlinmvp.data.DataManager
import com.kiethuynh.kotlinmvp.data.retrofit.request.UserRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kiethuynh on 19/09/2017
 */
class LoginPresenter @Inject constructor(private val mDataManager: DataManager) : BasePresenter<LoginContract.View, LoginContract.State>(), LoginContract.Presenter {


    override fun onAttachView(state: LoginContract.State?) {

    }

    private fun login(userRequest: UserRequest) {
        view?.loadingIndicator(true)
        mDataManager.getDataSource().login(userRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { token ->
                            view?.loadingIndicator(false)
                            mDataManager.getPreferencesHelper().setAccessToken(token)
                            view?.goToProfileScreen()
                        },
                        { throwable ->
                            view?.loadingIndicator(false)
                            ErrorHandler.handleError(throwable, this)
                        })
    }

    override fun onLoginButtonClick(username: String, password: String) {
        login(UserRequest(username, password))
    }

    override fun onDetachView() {
    }

    override fun onDestroy() {
    }
}